package com.neba.AI.Bot.service;

import com.neba.AI.Bot.model.GeneratedImage;
import org.springframework.ai.client.AiClient;
import org.springframework.ai.client.AiResponse;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Service
public class AiService {

    @Autowired
    AiClient aiClient;

    @Value("${spring.ai.openai.apikey}")
    private String apiKey;

    @Value("${spring.ai.openai.imageUrl}")
    private String openAIImageUrl;


    public String getJoke(String topic) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                Crafting a compilation of best jokes. Would you like me to create a joke about {topic}?
                """);
        promptTemplate.add("topic", topic);

        return this.aiClient.generate(promptTemplate.create()).getGeneration().getText();
    }
    public String getMotivationalQuote(String topic) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                I'm trying to motivate people, would you like me to create a best motivational quote based on {topic}?
                """);
        promptTemplate.add("topic", topic);

        return this.aiClient.generate(promptTemplate.create()).getGeneration().getText();
    }
    public String getDatingAdvice(String topic) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                I'm trying to get a best dating advice based on {topic}
                """);
        promptTemplate.add("topic", topic);

        return this.aiClient.generate(promptTemplate.create()).getGeneration().getText();
    }
    public String getRizz() {
        PromptTemplate promptTemplate = new PromptTemplate("""
              please give me the  best and funniest rizz lines for attracting romantically in one sentence?
                """);

        return this.aiClient.generate(promptTemplate.create()).getGeneration().getText();
    }
    public String getExplain(String topic) {
        PromptTemplate promptTemplate = new PromptTemplate("""
               give the  best explanation for a {topic} in detail?
                """);
        promptTemplate.add("topic", topic);

        return this.aiClient.generate(promptTemplate.create()).getGeneration().getText();
    }
    public String getFacts(String topic){
        PromptTemplate promptTemplate = new PromptTemplate("""
              give me the best fascinating facts in one sentence about {topic}
                """);
        promptTemplate.add("topic", topic);

        return this.aiClient.generate(promptTemplate.create()).getGeneration().getText();

    }
    public String getRandomFacts(){
        PromptTemplate promptTemplate = new PromptTemplate("""
             please give me the best fascinating facts in one sentence
                """);


        return this.aiClient.generate(promptTemplate.create()).getGeneration().getText();

    }


    public String getBook(String category, String year) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                I would like to research some books. Please give me a book about {category} in {year} to get started?
                But pick the best best you can think of. I'm a book critic. Ratings are great help.
                And who wrote it? And who help it? Can you give me a short plot summary and also it's name?
                But don't give me too much information. I don't want any spoilers.
                And please give me these details in the following JSON format: category, year, bookName, author, review, smallSummary.
                """);
        Map.of("category", category, "year", year).forEach(promptTemplate::add);
        AiResponse generate = this.aiClient.generate(promptTemplate.create());
        return generate.getGeneration().getText();
    }
    public String getMusicRecommendation(String genre, String year) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                I would like to get a music. Please give me a music about {genre} in {year} to listen?
                But pick the best best you can think of. I'm a music critic. best music are great help.
                And who sing it? And who produce it? Can you give me a short plot description of the music and also it's name?
                and artist small details?
                But don't give me too much information.
                And please give me these details in the following JSON format: music name, year, singer, producer, language, smallDescription about music.
                """);
        Map.of("genre", genre, "year", year).forEach(promptTemplate::add);
        AiResponse generate = this.aiClient.generate(promptTemplate.create());
        return generate.getGeneration().getText();
    }
    public String getMovieRecommendation(String category, String year) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                I would like to get a movie. Please give me a music about {category} in {year} to watch?
                But pick the best best you can think of. I'm a movie critic. best movie are great help.
                And who produce it? And who are the main actors? Can you give me a short plot description of the movie and also it's name?
                and artist small details?
                But don't give me too much information. but please no spoiler.
                And please give me these details in the following JSON format: music name, year, singer, producer, language, smallDescription about music.
                """);
        Map.of("category", category, "year", year).forEach(promptTemplate::add);
        AiResponse generate = this.aiClient.generate(promptTemplate.create());
        return generate.getGeneration().getText();
    }


    public InputStreamResource getImage(@RequestParam(name = "topic") String topic) throws URISyntaxException {
        PromptTemplate promptTemplate = new PromptTemplate("""
                 I am really bored from online memes. Can you create me a prompt about {topic}.
                 Elevate the given topic. Make it sophisticated.
                 Make a resolution of 256x256, but ensure that it is presented in json.
                 I want only one image creation. Give me as JSON format: prompt, n:1, size:256x256.
                """);
        promptTemplate.add("topic", topic);
        String imagePrompt = this.aiClient.generate(promptTemplate.create()).getGeneration().getText();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>(imagePrompt, headers);

        String imageUrl = restTemplate.exchange(openAIImageUrl, HttpMethod.POST, httpEntity, GeneratedImage.class)
                .getBody().getData().get(0).getUrl();
        byte[] imageBytes = restTemplate.getForObject(new URI(imageUrl), byte[].class);
        assert imageBytes != null;
        return new InputStreamResource(new java.io.ByteArrayInputStream(imageBytes));
    }


}

