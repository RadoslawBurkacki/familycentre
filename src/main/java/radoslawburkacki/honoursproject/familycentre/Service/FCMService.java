package radoslawburkacki.honoursproject.familycentre.Service;


import com.google.gson.*;
import com.squareup.okhttp.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import radoslawburkacki.honoursproject.familycentre.CrudRepo.FCMTokenRepository;
import radoslawburkacki.honoursproject.familycentre.Model.FCMToken;
import radoslawburkacki.honoursproject.familycentre.Model.Message;
import radoslawburkacki.honoursproject.familycentre.Model.User;

import java.util.List;

@Service
public class FCMService {

    @Autowired
    FCMTokenRepository fcmTokenRepository;

    public void saveFCMToken(FCMToken fcmToken) {
        System.out.println("Save FCMToken");

        if (fcmTokenRepository.findByMyFCMToken(fcmToken.getMyFCMToken()) != null) {
            System.out.println("@@@@@@@@");
            FCMToken fcmToken1 = fcmTokenRepository.findByMyFCMToken(fcmToken.getMyFCMToken());
            fcmToken1.setMyFCMToken("empty");
            fcmTokenRepository.save(fcmToken1);
        }

        fcmTokenRepository.save(fcmToken);
    }

    public void sendSOS(List<String> FCMtokenList, String name) {

        System.out.println("Sending SOS notification");

        for (String s : FCMtokenList) {
            final MediaType jsonMediaType = MediaType.parse("application/json");
            try {

                JsonObject android = new JsonObject();
                android.addProperty("priority", "high");

                JsonObject data = new JsonObject();
                data.addProperty("sos", name);

                JsonObject jsonObject = new JsonObject();
                jsonObject.add("data", data);
                jsonObject.add("android", android);

                jsonObject.addProperty("to", s);

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
    }



    public void sendNewUserNotification(List<String> FCMtokenList, String membername) {

        System.out.println("Sending notifcation about new user");

        for (String s : FCMtokenList) {
            final MediaType jsonMediaType = MediaType.parse("application/json");
            try {

                JsonObject android = new JsonObject();
                android.addProperty("priority", "high");


                JsonObject data = new JsonObject();
                data.addProperty("newfamilymember", membername);

                JsonObject jsonObject = new JsonObject();
                jsonObject.add("data", data);
                jsonObject.add("android", android);

                jsonObject.addProperty("to", s);

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

    }


    public void sendPrivateChatMessage(Message m) {

        System.out.println("\nSending private message from " + m.getFromId() + " to: " + m.getToId() + ". Message: " + m.getMessage()
                + "\nTo: " + fcmTokenRepository.findFCMTokenByUserId(m.getToId()).getMyFCMToken());

        final MediaType jsonMediaType = MediaType.parse("application/json");
        try {
            JsonObject android = new JsonObject();
            android.addProperty("priority", "high");


            JsonObject data = new JsonObject();
            data.addProperty("message", m.getMessage());
            data.addProperty("fromid", m.getFromId());
            data.addProperty("toid", m.getToId());
            data.addProperty("date", m.getDate());

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("data", data);
            jsonObject.add("android", android);

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

    public void sendUserRemovedNotification(List<String> FCMtokenList, User user){


        for (String s : FCMtokenList) {
            final MediaType jsonMediaType = MediaType.parse("application/json");
            try {

                JsonObject android = new JsonObject();
                android.addProperty("priority", "high");

                JsonObject data = new JsonObject();
                data.addProperty("firstname", user.getFname());
                data.addProperty("lastname", user.getLname());
                data.addProperty("userid", user.getId());

                JsonObject jsonObject = new JsonObject();
                jsonObject.add("data", data);
                jsonObject.add("android", android);

                jsonObject.addProperty("to", s);

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

    }


}
