package sample.com.buddyclouddroidtest;

import android.test.AndroidTestCase;

import junit.framework.TestCase;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sample.com.buddyclouddroidtest.model.Post;
import sample.com.buddyclouddroidtest.model.User;
import sample.com.buddyclouddroidtest.retrofit.BuddyCloudAdapter;
import sample.com.buddyclouddroidtest.retrofit.BuddyCloudHelper;
import sample.com.buddyclouddroidtest.retrofit.BuddyCloudRestApi;

public class BuddyCloudTest extends AndroidTestCase {

    public void testCreateUser() {
        BuddyCloudRestApi restApi = BuddyCloudAdapter.build();

        User user = new User();
        user.setEmail("haha@test.com");
        user.setUsername("destri15@buddycloud.org");
        user.setPassword("destri15");

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        restApi.createUser(user, new Callback<String>() {
            @Override
            public void success(String responseStr, Response response) {
                assertTrue(true);
                countDownLatch.countDown();
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("error " + error.getResponse());
                assertTrue(false);
                countDownLatch.countDown();
            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }

    public void testPostToNode() {
        BuddyCloudRestApi restApi = BuddyCloudAdapter.build();

        String authorization = BuddyCloudHelper.createBasicHttpAuthToken("destri15@buddycloud.org", "destri15");
        String channelId = "destri15@buddycloud.org";
        String nodeId = "posts";


        Post post = new Post();
        post.setContent("hahaha2");

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        restApi.post(authorization, channelId, nodeId, post, new Callback<String>() {
            @Override
            public void success(String responseStr, Response response) {
                assertTrue(true);
                countDownLatch.countDown();
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("error " + error.getResponse());
                assertTrue(false);
                countDownLatch.countDown();
            }
        });


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done");
    }

//    public void testCreateNewNode() {
//        BuddyCloudRestApi restApi = BuddyCloudAdapter.build();
//
//        String authorization = BuddyCloudHelper.createBasicHttpAuthToken("destri15@buddycloud.org", "destri15");
//        String channelId = "destri15@buddycloud.org";
//        String nodeId = "nodeTest1";
//
//
//        final CountDownLatch countDownLatch = new CountDownLatch(1);
//
//        restApi.createNode(authorization, channelId, nodeId, new Object(), new Callback<String>() {
//            @Override
//            public void success(String responseStr, Response response) {
//                assertTrue(true);
//                countDownLatch.countDown();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                System.out.println("error " + error.getResponse());
//                assertTrue(false);
//                countDownLatch.countDown();
//            }
//        });
//
//
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Done");
//    }
}