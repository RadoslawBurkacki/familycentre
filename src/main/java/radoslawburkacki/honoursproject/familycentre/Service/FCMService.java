package radoslawburkacki.honoursproject.familycentre.Service;


import com.google.gson.*;
import com.squareup.okhttp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import radoslawburkacki.honoursproject.familycentre.CrudRepo.FCMTokenRepository;
import radoslawburkacki.honoursproject.familycentre.Model.FCMToken;
import radoslawburkacki.honoursproject.familycentre.Model.Message;

import java.util.List;

@Service
public class FCMService {

    @Autowired
    FCMTokenRepository fcmTokenRepository;

    public void saveFCMToken(FCMToken fcmToken) {

        fcmTokenRepository.save(fcmToken);

    }


    public void notifyAboutSOS() {

    }


    public void notifyAboutNewFamilyMember() {

    }

    public void sendFamilyChatMessage() {

    }

    public void sendNewUserNotification(List<String> FCMtokenList, String membername) {

        System.out.println("Sending notifcation about new user");


        final MediaType jsonMediaType = MediaType.parse("application/json");
        try {

            JsonObject data = new JsonObject();
            data.addProperty("newfamilymember", membername);

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("data", data);

            for (String s : FCMtokenList) {
                jsonObject.addProperty("to", s);
            }



            RequestBody requestBody = RequestBody.create(jsonMediaType, new Gson().toJson(jsonObject));

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://fcm.googleapis.com/fcm/send")
                    .post(requestBody)
                    .addHeader("content-type", "application/json")
                    .addHeader("Authorization", "key=AIzaSyA-uBFG9bHbDz8IGOwU-evqXA3YWidDU58")
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println("aaa" + response.code() + response.body().string());

            response.body().close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    public void sendPrivateChatMessage(Message m) {

        System.out.println("Sending message");

        final MediaType jsonMediaType = MediaType.parse("application/json");
        try {

            //   JsonObject notification = new JsonObject();
            //    notification.addProperty("body", m.getMessage());


            JsonObject data = new JsonObject();
            data.addProperty("message", m.getMessage());
            data.addProperty("fromid", m.getFromId());
            data.addProperty("toid", m.getToId());
            data.addProperty("date", m.getDate());


            JsonObject jsonObject = new JsonObject();
            jsonObject.add("data", data);
            //a    jsonObject.add("notification", notification);

            jsonObject.addProperty("to", fcmTokenRepository.findFCMTokenByUserId(m.getToId()).getMyFCMToken());

            RequestBody requestBody = RequestBody.create(jsonMediaType, new Gson().toJson(jsonObject));

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://fcm.googleapis.com/fcm/send")
                    .post(requestBody)
                    .addHeader("content-type", "application/json")
                    .addHeader("Authorization", "key=AIzaSyA-uBFG9bHbDz8IGOwU-evqXA3YWidDU58")
                    .build();

            Response response = client.newCall(request).execute();

            System.out.println("aaa" + response.code() + response.body().string());

            response.body().close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }


        /*
        working notification
        final MediaType jsonMediaType = MediaType.parse("application/json");
        try {

            JsonObject a = new JsonObject();
            a.addProperty("body", "hello java123321");

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("to", UserToken);
            jsonObject.add("notification", a);


            RequestBody requestBody = RequestBody.create(jsonMediaType, new Gson().toJson(jsonObject));

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://fcm.googleapis.com/fcm/send")
                    .post(requestBody)
                    .addHeader("content-type", "application/json")
                    .addHeader("Authorization", "key=AIzaSyA-uBFG9bHbDz8IGOwU-evqXA3YWidDU58")
                    .build();

            Response response = client.newCall(request).execute();

            response.body().close();

        } catch (Exception e) {

        }*/

    }


}
