package com.app.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToolController {
    @FXML
    public StackPane PaneStage;

    @FXML
    private TextField text_field;

//    @FXML
//    private ImageView myimageview;


      @FXML
      private VBox MetaMainBox;


      List<HBox> bAHbox = new ArrayList<HBox>();

@FXML
private void initialize() {


}

   @FXML
   private void onSearch() throws JsonProcessingException, InterruptedException {
       MetaMainBox.getChildren().clear();
       Models.MainModels.RespStructure resp = new ObjectMapper().readValue(handleSearch(text_field.getText()), Models.MainModels.RespStructure.class);
       List<Models.MainModels.Result> games = resp.data.results.stream().filter(res -> res.type == 1 && res.highlight != null ).toList();
       int size = Math.min(games.size(), 3);
       for(int i = 0 ; i < size ; i++){
           MakeMetaBox(games.get(i));
       }
   }


    OkHttpClient client = new OkHttpClient();

    private String handleSearch(String newValue) {
        // Do something with the new text value
        String url = "https://www.mobygames.com/search/auto/?q="+
                newValue
                +"&p=false";
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

    private void MakeMetaBox(Models.MainModels.Result res){

        ImageView imgV = new ImageView();
        imgV.fitHeightProperty().setValue(100);
        imgV.fitWidthProperty().setValue(100);
        imgV.preserveRatioProperty().setValue(true);
//        lImgv.add(imgV);
//    imgV.setImage(imgUrl);



        Label nLabel = new Label(res.name);
        Button btn = new Button("add game");
        btn.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(res.name);
            }
        });
        nLabel.getStyleClass().add("labeltext");
        nLabel.setPadding(new Insets(10));

//        lLabel.add(nLabel);
        VBox nhbox = new VBox(nLabel,btn);
        MetaMainBox.getChildren().add(nhbox);

    }





}
