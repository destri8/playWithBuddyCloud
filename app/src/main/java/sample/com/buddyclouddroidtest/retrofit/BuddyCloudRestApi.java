package sample.com.buddyclouddroidtest.retrofit;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import sample.com.buddyclouddroidtest.model.Post;
import sample.com.buddyclouddroidtest.model.PostThread;
import sample.com.buddyclouddroidtest.model.User;

/**
 * Created by Destri on 5/18/15.
 */
public interface BuddyCloudRestApi {

    @Headers({
            "Content-Type: application/json",
            "charset: utf-8"
    })
    @POST("/account")
    void createUser(@Body User user, Callback<String> callback);

    @POST("/{channelid}/{nodeid}")
    void createNode(
            @Header("Authorization")String authorization,
            @Path("channelid")String channelId, @Path("nodeid")String nodeId,
            @Body Object emptyBody,
            Callback<String> callback);

    @Headers({
            "Content-Type: application/json",
            "charset: utf-8"
    })
    @POST("/{channelid}/content/{nodeid}")
    void post(
            @Header("Authorization")String authorization,
            @Path("channelid")String channelId, @Path("nodeid")String nodeId,
            @Body Post postContent,
            Callback<Post> callback);


    @Headers({
            "Accept: application/json",
            "charset: utf-8"
    })
    @GET("/{channelid}/subscribers/{nodeid}")
    Map<String, String> fetchFollower(
            @Header("Authorization")String authorization,
            @Path("channelid")String channelId, @Path("nodeid")String nodeId
    );


    @Headers({
            "Accept: application/json",
            "charset: utf-8"
    })
    @GET("/{channelid}/content/{nodeid}")
    List<Post> fetchLastPost(
            @Header("Authorization")String authorization,
            @Path("channelid")String channelId, @Path("nodeid")String nodeId,
            @Query("max") String maxPostCount
    );


    @Headers({
            "Accept: application/json",
            "charset: utf-8"
    })
    @GET("/{channelid}/content/{nodeid}/threads")
    List<PostThread> fetchLastPostThread(
            @Header("Authorization")String authorization,
            @Path("channelid")String channelId, @Path("nodeid")String nodeId,
            @Query("max") String maxPostCount
    );

}
