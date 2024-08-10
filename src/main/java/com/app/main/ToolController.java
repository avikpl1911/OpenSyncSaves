package com.app.main;

import com.almasb.fxgl.core.concurrent.AsyncTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class ToolController {

    @FXML
    private TextField text_field;

//    @FXML
//    private ImageView myimageview;


      @FXML
      private VBox MetaMainBox;


@FXML
private void initialize() {
//    Image image = new Image("https://cdn.mobygames.com/covers/3887154-harry-potter-and-the-chamber-of-secrets-gamecube-front-cover.jpg"); // Replace with your image path
//    myimageview.setImage(image);
 //   MakeMetaBox("https://cdn.mobygames.com/covers/3887154-harry-potter-and-the-chamber-of-secrets-gamecube-front-cover.jpg","harry");
}

   @FXML
   private void onSearch() throws JsonProcessingException {
       Models.MainModels.RespStructure resp = new ObjectMapper().readValue(handleSearch(text_field.getText()), Models.MainModels.RespStructure.class);
       List<Models.MainModels.Result> games = resp.data.results.stream().filter(res -> res.type == 1 && res.highlight != null ).toList();
       for(Models.MainModels.Result game : games){
           System.out.println(game.image);
           AsyncTask<Image> task = new AsyncTask<>() {
               @Override
               public Image await() {
                   return new Image(game.image);
               }
           };

           MakeMetaBox(task.await(),game.name);


       }
   }


    OkHttpClient client = new OkHttpClient();

    private String handleSearch(String newValue) {
        // Do something with the new text value
        String url = "https://www.mobygames.com/search/auto/?q=uncharted 4&p=false";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void MakeMetaBox(Image imgUrl , String name){

        ImageView imgV = new ImageView();
        imgV.fitHeightProperty().setValue(100);
        imgV.fitWidthProperty().setValue(100);
        imgV.preserveRatioProperty().setValue(true);
        imgV.setCache(true);
        imgV.setImage(imgUrl);

        Label nLabel = new Label(name);
//        nLabel.getStylesheets().add("style.css");
        nLabel.getStyleClass().add("labeltext");
        HBox nhbox = new HBox(imgV,nLabel);
        MetaMainBox.getChildren().add(nhbox);

    }




}
