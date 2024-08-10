package com.app.main;

import com.app.model.MainModels;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;

public class ToolController {

    @FXML
    private TextField text_field;

    @FXML
    private ImageView myimageview;

    @FXML
    private Label imageText;
//    @FXML
//    private void OnInputTextChange(){
//        String txt = text_field.getText();
//        System.out.println("hello");
//    }
@FXML
private void initialize() {
    Image image = new Image("https://cdn.mobygames.com/covers/3887154-harry-potter-and-the-chamber-of-secrets-gamecube-front-cover.jpg"); // Replace with your image path
    myimageview.setImage(image);
//    text_field.textProperty().addListener((observable, oldValue, newValue) -> {
//        // Your method to be executed on text change
//         //JSONO  handleTextFieldChange(newValue);
//    });

}

   @FXML
   private void onSearch() throws JsonProcessingException {
       ObjectMapper objectMapper = new ObjectMapper();
       Models.MainModels.RespStructure yourObject = objectMapper.readValue(handleSearch(text_field.getText()), Models.MainModels.RespStructure.class);
        System.out.println(yourObject.data.results.get(0).name);
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

    private void MakeMetaBox(){

    }

    static class LowerCaseWithUnderscoresNamingStrategy implements FieldNamingStrategy {
        @Override
        public String translateName(Field f) {
            return f.getName().replaceAll("([A-Z])", "_$1").toLowerCase();
        }
    }
}
