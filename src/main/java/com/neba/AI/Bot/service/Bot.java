package com.neba.AI.Bot.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Component
public class Bot extends TelegramLongPollingBot {
    private final AiService aiService;
    private String temporaryJokeValue;
    private String temporaryBookValue;
    private String temporaryBookYear;
    private String temporaryImageValue;
    private String temporaryMotivationalQuote;
    private String temporaryMusic;
    private String temporaryDating;
    private String temporaryMusicYear;
    private String temporaryFactName;
    private String temporaryExplainName;
    private String temporaryMovieName;
    private String temporaryMovieYear;
    private int stepMovie;

    private int stepMotivationalQuote;
    private int stepMusic;
    private int dating;
    private int stepImage;

    private int stepJoke;
    private int stepBook;
    private int stepExplain;
    private int stepFact;

    public Bot(AiService aiService) {
        this.aiService = aiService;
        this.temporaryJokeValue = "";
        this.temporaryBookValue = "";
        this.temporaryBookYear = "";
        this.temporaryImageValue = "";
        this.temporaryMotivationalQuote = "";
        this.temporaryDating ="";
        this.temporaryMusic = "";
        this.temporaryMusicYear = "";
        this.temporaryExplainName = "";
        this.temporaryFactName = "";
        this.temporaryMovieName = "";
        this.temporaryMovieYear = "";
        this.stepMovie =0;
        this.stepExplain = 0;
        this.stepFact = 0;
        this.stepImage = 0;
        this.stepMotivationalQuote = 0;
        this.stepJoke = 0;
        this.stepBook = 0;
        this.stepMusic =0;
        this.dating =0;

    }
    @Override
    public String getBotUsername() {

        return "";
    }
    @Override
    public String getBotToken() {

        return "";
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            long chatId = message.getChatId();
            String userName = message.getFrom().getUserName();
            String text = message.getText();
            if (text.equals("/joke")) {
                stepBook = 0;
                temporaryBookValue = "";
                stepImage = 0;
                temporaryImageValue = "";
                temporaryBookYear = "";
                stepMotivationalQuote = 0;
                temporaryMotivationalQuote = "";
                temporaryDating ="";
                temporaryMusic = "";
                temporaryMusicYear = "";
                temporaryFactName = "";
                temporaryExplainName = "";
                temporaryMovieName= "";
                temporaryMovieYear ="";
                stepMovie = 0;
                stepFact = 0;
                stepExplain=0;
                stepMusic =0;
                dating =0;
                askForJokeTopic(chatId);
                stepJoke = 1;


            } else if (temporaryJokeValue != null) {

                if (stepJoke == 1) {
                    temporaryJokeValue = text;
                    sendTextMessage(chatId, "noted! " + temporaryJokeValue);
                    sendTextMessage(chatId, "Now let's find you a joke based on " + temporaryJokeValue);

                    sendTextMessage(chatId, "Embrace the AI cringe");
                    sendTextMessage(chatId, "Here is your AI cringe joke");
                    sendJoke(chatId, temporaryJokeValue);
                    sendGift(chatId);
                    stepJoke = 0;
                    temporaryJokeValue = "";


                }


            }
            if (text.equals("/motivationalquote")) {
                stepBook = 0;
                stepJoke = 0;
                stepImage = 0;
                temporaryBookYear = "";
                temporaryBookValue = "";
                temporaryImageValue = "";
                temporaryJokeValue = "";
                temporaryDating ="";
                temporaryMusic = "";
                temporaryMusicYear = "";
                temporaryMovieYear ="";
                temporaryFactName = "";
                temporaryExplainName = "";
                temporaryMovieName= "";
                stepMovie = 0;
                stepFact = 0;
                stepExplain=0;
                stepMusic =0;
                dating =0;
                askForMotivationalQuoteTopic(chatId);
                stepMotivationalQuote = 1;

            } else if (temporaryMotivationalQuote != null) {
                if (stepMotivationalQuote == 1) {
                    temporaryMotivationalQuote = text;
                    sendTextMessage(chatId, "noted! " + temporaryMotivationalQuote);
                    sendTextMessage(chatId, "now let's find you a motivational quote based on " + temporaryMotivationalQuote);
                    sendMotivationalQuote(chatId, temporaryMotivationalQuote);
                    temporaryMotivationalQuote = "";
                    stepMotivationalQuote = 0;
                }


            }
            if (text.equals("/book")) {
                stepJoke = 0;
                temporaryJokeValue = "";
                stepImage = 0;
                temporaryImageValue = "";
                temporaryMotivationalQuote = "";
                stepMotivationalQuote = 0;
                temporaryDating ="";
                temporaryMusic = "";
                temporaryMusicYear = "";
                temporaryMovieYear ="";
                temporaryFactName = "";
                temporaryExplainName = "";
                temporaryMovieName= "";
                stepMovie = 0;
                stepFact = 0;
                stepExplain=0;
                stepMusic =0;
                dating =0;
                askForBookTopic(chatId);
                stepBook = 1;
            } else if (temporaryBookValue != null) {

                if (stepBook == 1) {
                    temporaryBookValue = text;
                    sendTextMessage(chatId, "noted! " + temporaryBookValue);
                    askForYear(chatId);
                    stepBook = 2;
                } else if (stepBook == 2) {
                    temporaryBookYear = text;
                    sendTextMessage(chatId, "noted! " + temporaryBookYear);
                    sendLongMessage(chatId, "Alright! now let me find you a book based on " + temporaryBookValue + " " + " & " + temporaryBookYear);
                    sendBook(chatId, temporaryBookValue, temporaryBookYear);
                    temporaryBookValue = "";
                    temporaryBookYear = "";
                    temporaryMusicYear = "";
                    stepBook = 0;


                }

            }
            if (text.equals("/image")) {
                stepBook = 0;
                stepJoke = 0;
                temporaryJokeValue = "";
                temporaryBookYear = "";
                temporaryBookValue = "";
                temporaryMotivationalQuote = "";
                stepMotivationalQuote = 0;
                temporaryMovieYear ="";
                temporaryDating ="";
                temporaryMusic = "";
                temporaryMusicYear = "";
                temporaryFactName = "";
                temporaryExplainName = "";
                temporaryMovieName= "";
                stepMovie = 0;
                stepFact = 0;
                stepExplain=0;
                stepMusic =0;
                dating =0;
                askForPhotoType(chatId);
                stepImage = 1;
            } else if (temporaryImageValue != null) {
                if (stepImage == 1) {
                    temporaryImageValue = text;
                    sendTextMessage(chatId, "noted! " + temporaryImageValue);
                    sendTextMessage(chatId, "now let's generate an image based on " + temporaryImageValue);
                    sendImage(chatId, temporaryImageValue);
                    stepImage = 0;
                    temporaryImageValue = "";
                }


            }
            if (text.equals("/datingadvice")) {
                stepBook = 0;
                stepJoke = 0;
                temporaryJokeValue = "";
                temporaryBookYear = "";
                temporaryBookValue = "";
                temporaryMotivationalQuote = "";
                temporaryMovieYear ="";
                temporaryFactName = "";
                temporaryExplainName = "";
                temporaryMovieName= "";
                stepMovie = 0;
                stepFact = 0;
                stepExplain=0;
                stepMotivationalQuote = 0;
              temporaryImageValue = "";
              stepImage=0;
                temporaryMusic = "";
                temporaryMusicYear = "";
                stepMusic =0;
                askForDatingTopic(chatId);
                dating =1;


            }
            else if (temporaryDating != null){
                if (dating == 1){
                    temporaryDating = text;
                    sendTextMessage(chatId, "noted! " + temporaryDating);
                    sendTextMessage(chatId, "now let's generate a dating advice based on " + temporaryDating);
                    sendDatingAdvice(chatId, temporaryDating);
                    dating =0;
                    temporaryDating = "";


                }
            }
            if (text.equals("/music")){
                stepBook = 0;
                stepJoke = 0;
                temporaryJokeValue = "";
                temporaryBookYear = "";
                temporaryBookValue = "";
                temporaryMotivationalQuote = "";
                stepMotivationalQuote = 0;
                temporaryMovieYear ="";
                temporaryImageValue = "";
                temporaryFactName = "";
                temporaryExplainName = "";
                temporaryMovieName= "";
                stepMovie = 0;
                stepFact = 0;
                stepExplain=0;
                stepImage=0;
               temporaryDating = "";
               dating =0;
               askForMusicGenre(chatId);
               stepMusic=1;

            } else if (temporaryMusic != null) {
                if (stepMusic == 1){
                    temporaryMusic = text;
                    sendLongMessage(chatId, "noted! " + temporaryMusic);
                    askForYear(chatId);
                    stepMusic =2;
                }
                else if (stepMusic == 2){
                    temporaryMusicYear = text;
                    sendTextMessage(chatId, "noted! " + temporaryMusicYear);
                    sendLongMessage(chatId, "Alright! now let me find you a music based on " + temporaryMusic + " " + " & " + temporaryMusicYear);
                    sendMusicRecommendation(chatId, temporaryMusic, temporaryMusicYear);
                    temporaryMusic = "";
                    temporaryMusicYear = "";
                    stepMusic =0;
                }



            }
            if (text.equals("/rizz")){
                stepBook = 0;
                stepJoke = 0;
                temporaryJokeValue = "";
                temporaryBookYear = "";
                temporaryBookValue = "";
                temporaryMotivationalQuote = "";
                stepMotivationalQuote = 0;
                temporaryImageValue = "";
                temporaryFactName = "";
                temporaryExplainName = "";
                temporaryMovieName= "";
                temporaryMovieYear ="";
                stepMovie = 0;
                stepFact = 0;
                stepExplain=0;
                stepImage=0;
                temporaryDating = "";
                dating =0;
                temporaryMusic = "";
                stepMusic = 0;
                sendTextMessage(chatId, "Embrace the AI cringe");
                sendTextMessage(chatId, "Here is your rizz");
            sendRizz(chatId);
            sendGift(chatId);
            }
            if (text.equals("/randomfact")){
                stepBook = 0;
                stepJoke = 0;
                temporaryJokeValue = "";
                temporaryBookYear = "";
                temporaryBookValue = "";
                temporaryMotivationalQuote = "";
                stepMotivationalQuote = 0;
                temporaryImageValue = "";
                temporaryMovieYear ="";
                temporaryFactName = "";
                temporaryExplainName = "";
                temporaryMovieName= "";
                stepMovie = 0;
                stepFact = 0;
                stepExplain=0;
                stepImage=0;
                temporaryDating = "";
                dating =0;
                temporaryMusic = "";
                stepMusic = 0;
             sendRandomFacts(chatId);
            }
            if (text.equals("/fact")){
                stepBook = 0;
                stepJoke = 0;
                temporaryJokeValue = "";
                temporaryBookYear = "";
                temporaryBookValue = "";
                temporaryMovieYear ="";
                temporaryMotivationalQuote = "";
                stepMotivationalQuote = 0;
                temporaryImageValue = "";
                temporaryExplainName = "";
                temporaryMovieName= "";
                stepMovie = 0;
                stepExplain=0;
                stepImage=0;
                temporaryDating = "";
                dating =0;
                temporaryMusic = "";
                stepMusic = 0;
                askForFactName(chatId);
                stepFact =1;
            } else if (temporaryFactName!=null) {
                if (stepFact ==1) {
                    temporaryFactName = text;
                    sendTextMessage(chatId, "noted!" + temporaryFactName);
                    sendTextMessage(chatId, "now lets generate you a facts based on " + temporaryFactName );
                    sendFacts(chatId, temporaryFactName);
                    temporaryFactName = "";
                    stepFact =0;

                }

            }
            if (text.equals("/explain")){
                stepBook = 0;
                stepJoke = 0;
                temporaryJokeValue = "";
                temporaryBookYear = "";
                temporaryBookValue = "";
                temporaryMotivationalQuote = "";
                stepMotivationalQuote = 0;
                temporaryImageValue = "";
                temporaryMovieYear ="";
                temporaryFactName = "";
                temporaryMovieName= "";
                stepMovie = 0;
                stepFact = 0;
                stepImage=0;
                temporaryDating = "";
                dating =0;
                temporaryMusic = "";
                stepMusic = 0;
                askForExplainTopic(chatId);
                stepExplain =1;

            }
            else if (temporaryExplainName != null){
                if (stepExplain ==1){
                    temporaryExplainName = text;
                    sendTextMessage(chatId, "noted!" + temporaryExplainName);
                    sendTextMessage(chatId, "now lets generate you a explanation based on " + temporaryExplainName );
                    sendExplanation(chatId, temporaryExplainName);
                    stepExplain =0;
                    temporaryExplainName = "";

                }
            }
            if (text.equals("/movie")){
                stepBook = 0;
                stepJoke = 0;
                temporaryJokeValue = "";
                temporaryBookYear = "";
                temporaryBookValue = "";
                temporaryMotivationalQuote = "";
                stepMotivationalQuote = 0;
                temporaryImageValue = "";
                temporaryFactName = "";
                temporaryExplainName = "";
                stepFact = 0;
                stepExplain=0;
                stepImage=0;
                temporaryDating = "";
                dating =0;
                temporaryMusic = "";
                stepMusic = 0;
                askForMovieCategory(chatId);
                stepMovie =1;
            } else if (temporaryMovieName != null) {
                if (stepMovie == 1) {
                    temporaryMovieName = text;
                    sendTextMessage(chatId, "noted! " + temporaryMovieName);
                    askForYear(chatId);
                    stepMovie =2;
                } else if (stepMovie == 2) {
                    temporaryMovieYear = text;
                    sendTextMessage(chatId, "noted! " + temporaryMovieYear);
                    sendTextMessage(chatId, "Now let's generate you a movie based on " + temporaryMovieName + " & " + temporaryMovieYear);
                     sendMovieRecommendation(chatId, temporaryMovieName, temporaryMovieYear);
                      stepMovie =0;
                      temporaryMovieYear ="";
                      temporaryMovieName = "";

                }
            }

            if (text.equals("/start")) {
                stepBook = 0;
                stepJoke = 0;
                temporaryJokeValue = "";
                temporaryBookYear = "";
                temporaryMovieYear ="";
                temporaryBookValue = "";
                temporaryMotivationalQuote = "";
                stepMotivationalQuote = 0;
                temporaryImageValue = "";
                temporaryFactName = "";
                temporaryExplainName = "";
                temporaryMovieName= "";
                stepMovie = 0;
                stepFact = 0;
                stepExplain=0;
                stepImage=0;
                temporaryDating = "";
                dating =0;
                temporaryMusic = "";
                stepMusic = 0;
                sendLongMessage(chatId, "Selam " + " " + userName + " Welcome to ቻት GPT bot, to get description of the bot please click /description");
            }
            if (text.equals("/description")) {
                stepBook = 0;
                stepJoke = 0;
                temporaryJokeValue = "";
                temporaryMovieYear ="";
                temporaryBookYear = "";
                temporaryBookValue = "";
                temporaryMotivationalQuote = "";
                stepMotivationalQuote = 0;
                temporaryImageValue = "";
                temporaryFactName = "";
                temporaryExplainName = "";
                temporaryMovieName= "";
                stepMovie = 0;
                stepFact = 0;
                stepExplain=0;
                stepImage=0;
                temporaryDating = "";
                dating =0;
                temporaryMusic = "";
                stepMusic = 0;
                sendLongMessage(chatId, "In this bot you can receive AI based " +
                        "explanation of anything, random facts,facts based on topic, jokes, book recommendation by topic and year, motivational quote,music recommendation by genre and year image generation, dating advice, best rizz and so on. I have used OpenAi api for implementation. This bot was developed by @nebaneba1");


            }

        }
    }

    public void sendTextMessage(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            sendTextMessage(chatId, " An error occurred");

        }
    }
    private void sendLongMessage(long chatId, String longMessage) {
        final int MAX_MESSAGE_LENGTH = 4096;
        if (longMessage.length() <= MAX_MESSAGE_LENGTH) {
            sendTextMessage(chatId, longMessage);
        } else {

            for (int i = 0; i < longMessage.length(); i += MAX_MESSAGE_LENGTH) {
                int endIndex = Math.min(i + MAX_MESSAGE_LENGTH, longMessage.length());
                String chunk = longMessage.substring(i, endIndex);
                sendTextMessage(chatId, chunk);
            }
        }
    }
    private void sendGift(Long chatId) {
        String gifUrl = "https://tenor.com/view/teletubbies-dies-of-cringe-meme-gif-27676616";

        try {
            InputStream inputStream = new URL(gifUrl).openStream();

            InputFile gifFile = new InputFile(inputStream, "gift.gif");

            SendVideo sendVideo = new SendVideo();
            sendVideo.setChatId(chatId);
               sendVideo.setVideo(gifFile);


            execute(sendVideo);

            inputStream.close();
        } catch (IOException | TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private void sendImage(long chatId, String topic) {
        try {
            InputStreamResource imageResource = aiService.getImage(topic);

            String caption = "Here's an image about " + topic;
            sendPhoto(chatId, imageResource, caption);
        } catch (Exception e) {
            e.printStackTrace();
            sendTextMessage(chatId, "An error occurred while generating the image.");
        }
    }

    private void sendPhoto(long chatId, InputStreamResource imageResource, String caption) throws IOException {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(chatId);
        sendPhotoRequest.setPhoto(new InputFile(imageResource.getInputStream(), "photo.jpeg"));
        sendPhotoRequest.setCaption(caption);


        try {
            execute(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            sendTextMessage(chatId, "An error occurred while sending the photo.");
        }
    }

    private void askForJokeTopic(long chatId) {


        sendLongMessage(chatId, "What kind of joke would you like? Please provide a topic.");
    }
    private void askForMovieCategory(long chatId) {


        sendLongMessage(chatId, "What kind of movie would you like? Please provide a category");
    }
    private void askForMotivationalQuoteTopic(long chatId) {


        sendLongMessage(chatId, "What kind of motivational quote would you like? please provide in detail.");
    }
    private void askForBookTopic(long chatId) {

        sendLongMessage(chatId, "What book topic are you interested in?");
    }
    private void askForMusicGenre(long chatId) {

        sendLongMessage(chatId, "What genre are you interested in?");
    }
    private void askForDatingTopic(long chatId) {

        sendLongMessage(chatId, "What advice you would like to hear? please provide what you are feeling or thinking");
    }
    private void askForFactName(long chatId) {

        sendTextMessage(chatId, "What topic would you like to hear facts about? You can suggest artists, civilizations, or anything else.");
    }
    private void askForExplainTopic(long chatId) {

        sendTextMessage(chatId, "What topic would you like me to explain to you?");
    }
    private void askForPhotoType(long chatId) {

        sendTextMessage(chatId, "What kind of image would you like? Please provide a topic in detail.");
    }
    private void askForYear(long chatId) {

        sendLongMessage(chatId, "Please provide the year");
    }
    private void sendDatingAdvice(long chatId, String advice){
        String advice1 = aiService.getDatingAdvice(advice);
        sendLongMessage(chatId, advice1);
    }
    private void sendMovieRecommendation(long chatId, String category,String year){
        String movie = aiService.getMovieRecommendation(category,year);
        sendLongMessage(chatId, movie);
    }
    private void sendRizz(long chatId){
        String rizz = aiService.getRizz();
        sendLongMessage(chatId, rizz);
    }
    private void sendRandomFacts(long chatId){
        String facts = aiService.getRandomFacts();
        sendLongMessage(chatId, facts);
    }
    private void sendExplanation(long chatId, String message){
        String explanation = aiService.getExplain(message);
        sendLongMessage(chatId, explanation);
    }
    private void sendFacts(long chatId, String topic){
        String facts = aiService.getFacts(topic);
        sendLongMessage(chatId, facts);
    }
    private void sendMusicRecommendation(long chatId, String genre, String year){
        String musicRecommend = aiService.getMusicRecommendation(genre, year);
        sendLongMessage(chatId, musicRecommend);
    }


    private void sendJoke(long chatId, String topic) {

        String joke = aiService.getJoke(topic);
        sendLongMessage(chatId, joke);
    }
    private void sendMotivationalQuote(long chatId, String topic) {

        String joke = aiService.getMotivationalQuote(topic);
        sendLongMessage(chatId, joke);
    }
    private void sendBook(long chatId, String category,String year){
        String book = aiService.getBook(category, year);

        sendLongMessage(chatId, book);
    }




// if you want to try it by yourself, add your own bot name and api token from bot father. I already removed mine therefore you can't steal it:)



}
