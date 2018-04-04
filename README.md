

# Welcome to UizaSDK

# Importing the Library
**Step 1. Add the JitPack repository to your build file**  

    allprojects {  
          repositories {  
             ...  
             maven { url 'https://jitpack.io' }  
          }   }
**Step 2. Add the dependency**  

    defaultConfig {  
      ...  
      multiDexEnabled  true  
    }
    ...
    dependencies {  
      compile 'com.github.tplloi:basemaster:1.0.3'  
      compile 'com.android.support:multidex:1.0.3'  
    }

# Init:
**Put these code below into onCreate() of Apllication class:**  

    String currentPlayerId = "enter id of player";  
    String currentApiEndPoint = "enter url api end point;  
    String currentApiTrackingEndPoint = "enter url api tracking end point";  
      
    String token = "enter your token";  
    RestClientV2.init(currentApiEndPoint, token);  
    UizaData.getInstance().init(currentApiEndPoint, currentApiTrackingEndPoint, token, currentPlayerId);  

# How to call API?:
**Step1: You must extend your activity/fragment like this**  

    public class YourActivity extends BaseActivity{
    ...
    }

or

    public class YourFragment extends BaseFragment{
    }
    
**Step 2: Call api by using this function** 

    private void getListAllMetadata() {  
        UizaService service = RestClientV2.createService(UizaService.class);  
        int limit = 50;  
        String orderBy = "name";  
        String orderType = "ASC";  
      
        JsonBodyMetadataList jsonBodyMetadataList = new JsonBodyMetadataList();  
        jsonBodyMetadataList.setLimit(limit);  
        jsonBodyMetadataList.setOrderBy(orderBy);  
        jsonBodyMetadataList.setOrderType(orderType);  
      
        subscribe(service.listAllMetadataV2(jsonBodyMetadataList), new ApiSubscriber<ListAllMetadata>() {  
            @Override  
            public void onSuccess(ListAllMetadata listAllMetadata) {  
                //do sth  
            }  
      
            @Override  
            public void onFail(Throwable e) {    
                handleException(e);  
            }  
        });  
    }

**More ex:**

@ http://dev-api.uiza.io/resource/index.html#api-Credentical-Auth:

    subscribe(service.auth(jsonBodyAuth), new ApiSubscriber<...>() {  
    ...
    });
*Note: You must save Auth object when calling this api success.
Using this codes below:*

    LPref.setAuth(activity, auth, new Gson());

@ http://dev-api.uiza.io/resource/index.html#api-Credentical-Check_Token

    subscribe(service.checkToken(), new ApiSubscriber<...>() {  
    ...
    });
*Note: You must save Auth object when calling this api success.
Using this codes below:*

    LPref.setAuth(activity, auth, new Gson());

@ http://dev-api.uiza.io/resource/index.html#api-Metadata-List_All_Metadata

    subscribe(service.listAllMetadataV2(jsonBodyMetadataList), new ApiSubscriber<...>() {  
    ...
    });
@ http://dev-api.uiza.io/resource/index.html#api-Entity-List_All_Entity

    subscribe(service.listAllEntityV2(jsonBodyListAllEntity), new ApiSubscriber<...>() {  
    ...
    });
@ http://dev-api.uiza.io/resource/index.html#api-Entity-Get_Detail_Entity

    subscribe(service.getDetailEntityV2(jsonBodyGetDetailEntity), new ApiSubscriber<...>() {  
    ...
    });
  @ http://dev-api.uiza.io/resource/index.html#api-Entity-Get_Link_Download
  

    subscribe(service.getLinkPlayV2(jsonBodyGetLinkPlay), new ApiSubscriber<...>() {  
     ...
    });

@ http://dev-api.uiza.io/resource/index.html#api-Entity-List_All_Entity_Relation

    subscribe(service.getListAllEntityRalationV2(jsonBodyListAllEntityRelation), new ApiSubscriber<...>() {  
    ...
    });

@ http://dev-api.uiza.io/resource/index.html#api-Search-Search_Metadata

    subscribe(service.searchEntityV2(jsonBodySearch), new ApiSubscriber<...>() {  
    ...
    });


# How to play the video?:
You only call PlayerScreenActivity of UizaSDK, this screen will play video with its controller and display video 's information

    Intent intent = new Intent(activity, UizaPlayerActivityV2.class);    
    intent.putExtra(Constants.KEY_UIZA_ENTITY_ID, "enter entity id");  
    intent.putExtra(Constants.KEY_UIZA_ENTITY_COVER, "enter video cover|thumnail or img url you like here");  
    intent.putExtra(Constants.KEY_UIZA_ENTITY_TITLE, "enter video name|title here");  
    startActivity(intent);  
    LActivityUtil.tranIn(activity);//go to PlayerScreenActivity with animation.

![alt text](https://github.com/uizaio/uiza-sdk-player-android/blob/master/gif/uiza_player_screen.gif?raw=true)

# Manual Simple Uiza Video Player:
**Step 1: Create xml layout file (activity_simple_uiza_player.xml) and add this frame layout below**

    <FrameLayout  
      android:id="@+id/container_uiza_video"  
      android:layout_width="match_parent"  
      android:layout_height="wrap_content" /> 

**Step 2: In your Activity, must extends BaseActivity**

    public class SimpleUizaPlayerActivity extends BaseActivity {
    ...
    }

**Step 3: Open manifest**
		

    <activity  
      android:name=".SimpleUizaPlayerActivity"  
      android:configChanges="keyboard|keyboardHidden|orientation|screenSize|screenLayout|smallestScreenSize|uiMode"  
      android:screenOrientation="portrait">  
        <intent-filter>  
            <action android:name="com.google.android.exoplayer.demo.action.VIEW" />  
            <category android:name="android.intent.category.DEFAULT" />  
      
            <data android:scheme="http" />  
            <data android:scheme="https" />  
            <data android:scheme="content" />  
            <data android:scheme="asset" />  
            <data android:scheme="file" />  
        </intent-filter>  
        <intent-filter>  
            <action android:name="com.google.android.exoplayer.demo.action.VIEW_LIST" />  
            <category android:name="android.intent.category.DEFAULT" />  
        </intent-filter>  
    </activity>

**Step 4: Open main class**

4.1: Add global variables:
	
    private FrameLayout containerUizaVideo;  
    private FrmUizaVideoV2 frmUizaVideoV2;
4.2: Add onCreate():

    containerUizaVideo = (FrameLayout) findViewById(io.uiza.sdk.ui.R.id.container_uiza_video);  
    initContainerVideo();
4.3: Config video:

    private void initContainerVideo() {  
        String entityId = "Enter entity id";  
        String entityCover = "Enter url entity cover";  
        String entityTitle = "Enter title's video";  
      
        InputModel inputModel = UizaUIUtil.createInputModel(entityId, entityCover, entityTitle);  
        UizaData.getInstance().setInputModel(inputModel);  
      
        List<String> listLinkPlay = new ArrayList<>();  
        listLinkPlay.add("Add your linkplay");    
        UizaData.getInstance().setLinkPlay(listLinkPlay);  
      
        frmUizaVideoV2 = new FrmUizaVideoV2();  
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();  
        transaction.replace(containerUizaVideo.getId(), frmUizaVideoV2);  
        transaction.commit();  
    }
4.4: Override onConfigurationChanged and edit like below:

    @Override  
    public void onConfigurationChanged(Configuration newConfig) {  
        LLog.d(TAG, "onConfigurationChanged");  
        super.onConfigurationChanged(newConfig);  
        UizaScreenUtil.toggleFullscreen(activity);  
        UizaUIUtil.setSizeOfContainerVideo(containerUizaVideo, frmUizaVideoV2);  
    }

4.5: Callback:

    frmUizaVideoV2.setWrapperCallback(new WrapperCallback() {  
        @Override  
      public void initializePlayer(Uri[] uris) {  
            LLog.d(TAG, "init video with array of linkplay");  
        }  
      
        @Override  
      public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {   
        }  
      
        @Override  
      public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {  
        }  
      
        @Override  
      public void onLoadingChanged(boolean isLoading) {  
        }  
      
        @Override  
      public void onRepeatModeChanged(int repeatMode) {     
        }  
      
        @Override  
      public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {  
        }  
      
        @Override  
      public void onTimelineChanged(Timeline timeline, Object manifest) {  
        }  
      
        @Override  
      public void onTrackVideoStart() {  
            UizaUIUtil.setSizeOfContainerVideo(containerUizaVideo, frmUizaVideoV2);  
        }  
      
        @Override  
      public void onTrackVideoView() {  
        }  
      
        @Override  
      public void onTrackPlayThrough(int percentOfVideoDuration) {  
        }  
      
        @Override  
      public void onPlaybackControllerClickSetting() {   
        }  
      
        @Override  
      public void onClickItemPlayList(Item item, int position) {  
        }  
      
        @Override  
      public void onVisibilityChange(int visibility) {  
        }  
      
        @Override  
      public void onErrorNoLinkPlay() {  
        }  
      
        @Override  
      public void onErrorCannotPlayAnyLinkPlay() {   
        }  
      
        @Override  
      public void onReleasePlayer() {  
        }  
      
        @Override  
      public void onAdLoadError(IOException error) {  
        }  
      
        @Override  
      public void onAdClicked() {  
        }  
      
        @Override  
      public void onAdTapped() {  
        }  
    });
