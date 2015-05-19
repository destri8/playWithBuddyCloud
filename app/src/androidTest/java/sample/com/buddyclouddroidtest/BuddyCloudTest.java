package sample.com.buddyclouddroidtest;

import android.test.AndroidTestCase;

import junit.framework.TestCase;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sample.com.buddyclouddroidtest.model.Post;
import sample.com.buddyclouddroidtest.model.PostThread;
import sample.com.buddyclouddroidtest.model.User;
import sample.com.buddyclouddroidtest.retrofit.BuddyCloudAdapter;
import sample.com.buddyclouddroidtest.retrofit.BuddyCloudHelper;
import sample.com.buddyclouddroidtest.retrofit.BuddyCloudRestApi;

public class BuddyCloudTest extends AndroidTestCase {

    public void testCreateUser() {
        BuddyCloudRestApi restApi = BuddyCloudAdapter.build();

        User user = new User();
        user.setEmail("haha@test.com");
        user.setUsername("destri17@buddycloud.org");
        user.setPassword("destri17");

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
        post.setContent("haha10");

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        restApi.post(authorization, channelId, nodeId, post, new Callback<Post>() {
            @Override
            public void success(Post resultPost, Response response) {
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

    public void testFetchFollower() {

        BuddyCloudRestApi restApi = BuddyCloudAdapter.build();

        String authorization = BuddyCloudHelper.createBasicHttpAuthToken("destri15@buddycloud.org", "destri15");
        String channelId = "destri15@buddycloud.org";
        String nodeId = "posts";

        Map<String, String> resultMap = restApi.fetchFollower(authorization, channelId, nodeId);

        System.out.println("Done");
    }

    public void testFetchLastPost() {
        //semua post, termasuk comment/post in reply to another post juga masuk

        BuddyCloudRestApi restApi = BuddyCloudAdapter.build();

        String authorization = BuddyCloudHelper.createBasicHttpAuthToken("destri15@buddycloud.org", "destri15");
        String channelId = "destri15@buddycloud.org";
        String nodeId = "posts";

        List<Post> allPostList = restApi.fetchLastPost(authorization, channelId, nodeId, null);

        List<Post> limitPostList = restApi.fetchLastPost(authorization, channelId, nodeId, "5");

        assertTrue(limitPostList.size() <= 5);

        /*
                [
                  {
                    "id": "61eaac51-0477-4e85-be8f-3210aa8189c9",
                    "author": "destri15@buddycloud.org",
                    "published": "2015-05-19T03:51:44.314Z",
                    "updated": "2015-05-19T03:51:44.314Z",
                    "content": "5",
                    "media": null
                  },
                  {
                    "id": "b3cab485-cc6f-4d67-a932-88eb8ba9e340",
                    "author": "destri10@buddycloud.org",
                    "published": "2015-05-19T03:58:08.875Z",
                    "updated": "2015-05-19T03:58:08.875Z",
                    "content": "5b by destri10",
                    "media": null,
                    "replyTo": "61eaac51-0477-4e85-be8f-3210aa8189c9"
                  },
                  {
                    "id": "42ed8a3d-d205-4f64-af47-7d9bca509147",
                    "author": "destri15@buddycloud.org",
                    "published": "2015-05-19T03:51:53.299Z",
                    "updated": "2015-05-19T03:51:53.299Z",
                    "content": "5 a",
                    "media": null,
                    "replyTo": "61eaac51-0477-4e85-be8f-3210aa8189c9"
                  },
                  {
                    "id": "64bd5264-8bc5-4b92-bba2-3beae667c839",
                    "author": "destri15@buddycloud.org",
                    "published": "2015-05-19T03:51:42.049Z",
                    "updated": "2015-05-19T03:51:42.049Z",
                    "content": "4",
                    "media": null
                  },
                  {
                    "id": "e70c4a8b-e4bb-460b-b568-9fae4a268238",
                    "author": "destri15@buddycloud.org",
                    "published": "2015-05-19T03:51:39.351Z",
                    "updated": "2015-05-19T03:51:39.351Z",
                    "content": "3",
                    "media": null
                  }
                ]

         */
    }

    public void testFetchLastPostThread() {
        //jumlah count dihitung dari post thread, comment tidak termasuk

        BuddyCloudRestApi restApi = BuddyCloudAdapter.build();

        String authorization = BuddyCloudHelper.createBasicHttpAuthToken("destri15@buddycloud.org", "destri15");
        String channelId = "destri15@buddycloud.org";
        String nodeId = "posts";

        List<PostThread> allPostList = restApi.fetchLastPostThread(authorization, channelId, nodeId, null);

        List<PostThread> limitPostList = restApi.fetchLastPostThread(authorization, channelId, nodeId, "5");

        assertTrue(limitPostList.size() <= 5);

        /* contoh hasil
                [
                  {
                    "id": "53513c35-d48f-455b-a660-a3092a690742",
                    "updated": "2015-05-19T04:25:37.420Z",
                    "items": [
                      {
                        "id": "53513c35-d48f-455b-a660-a3092a690742",
                        "author": "destri15@buddycloud.org",
                        "published": "2015-05-19T04:25:37.420Z",
                        "updated": "2015-05-19T04:25:37.420Z",
                        "content": "haha6",
                        "media": null
                      }
                    ]
                  },
                  {
                    "id": "61eaac51-0477-4e85-be8f-3210aa8189c9",
                    "updated": "2015-05-19T03:58:08.911Z",
                    "items": [
                      {
                        "id": "42ed8a3d-d205-4f64-af47-7d9bca509147",
                        "author": "destri15@buddycloud.org",
                        "published": "2015-05-19T03:51:53.299Z",
                        "updated": "2015-05-19T03:51:53.299Z",
                        "content": "5 a",
                        "media": null,
                        "replyTo": "61eaac51-0477-4e85-be8f-3210aa8189c9"
                      },
                      {
                        "id": "b3cab485-cc6f-4d67-a932-88eb8ba9e340",
                        "author": "destri10@buddycloud.org",
                        "published": "2015-05-19T03:58:08.875Z",
                        "updated": "2015-05-19T03:58:08.875Z",
                        "content": "5b by destri10",
                        "media": null,
                        "replyTo": "61eaac51-0477-4e85-be8f-3210aa8189c9"
                      },
                      {
                        "id": "61eaac51-0477-4e85-be8f-3210aa8189c9",
                        "author": "destri15@buddycloud.org",
                        "published": "2015-05-19T03:51:44.314Z",
                        "updated": "2015-05-19T03:51:44.314Z",
                        "content": "5",
                        "media": null
                      }
                    ]
                  },
                  {
                    "id": "64bd5264-8bc5-4b92-bba2-3beae667c839",
                    "updated": "2015-05-19T03:51:42.049Z",
                    "items": [
                      {
                        "id": "64bd5264-8bc5-4b92-bba2-3beae667c839",
                        "author": "destri15@buddycloud.org",
                        "published": "2015-05-19T03:51:42.049Z",
                        "updated": "2015-05-19T03:51:42.049Z",
                        "content": "4",
                        "media": null
                      }
                    ]
                  },
                  {
                    "id": "e70c4a8b-e4bb-460b-b568-9fae4a268238",
                    "updated": "2015-05-19T03:51:39.351Z",
                    "items": [
                      {
                        "id": "e70c4a8b-e4bb-460b-b568-9fae4a268238",
                        "author": "destri15@buddycloud.org",
                        "published": "2015-05-19T03:51:39.351Z",
                        "updated": "2015-05-19T03:51:39.351Z",
                        "content": "3",
                        "media": null
                      }
                    ]
                  },
                  {
                    "id": "b487d925-af07-448e-94a2-731ffb296a4b",
                    "updated": "2015-05-18T10:27:44.806Z",
                    "items": [
                      {
                        "id": "b487d925-af07-448e-94a2-731ffb296a4b",
                        "author": "destri15@buddycloud.org",
                        "published": "2015-05-18T10:27:44.806Z",
                        "updated": "2015-05-18T10:27:44.806Z",
                        "content": "hahaha2",
                        "media": null
                      }
                    ]
                  }
                ]

         */

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