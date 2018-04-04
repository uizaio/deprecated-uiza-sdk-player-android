
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

```
![alt text](https://github.com/uizaio/uiza-sdk-player-android/blob/master/gif/uiza_player_screen.gif?raw=true)
```