package com.uiza.player.ui.player.v2;

/**
 * Created by www.muathu@gmail.com on 12/24/2017.
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm;
import com.google.android.exoplayer2.drm.HttpMediaDrmCallback;
import com.google.android.exoplayer2.drm.UnsupportedDrmException;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;
import com.uiza.player.ext.ima.ImaAdsLoader;
import com.uiza.player.ext.ima.ImaAdsMediaSource;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.util.UizaScreenUtil;
import com.uiza.player.ui.util.UizaUIUtil;
import com.uiza.player.ui.views.DebugTextViewHelper;
import com.uiza.player.ui.views.PlaybackControlView;
import com.uiza.player.ui.views.SimpleExoPlayerView;
import com.uiza.player.ui.views.helper.EventLogger;
import com.uiza.player.ui.views.helper.InputModel;
import com.uiza.player.ui.views.helper.TrackSelectionHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.UUID;

import io.uiza.sdk.ui.BuildConfig;
import io.uiza.sdk.ui.R;
import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LPref;
import vn.loitp.data.EventBusData;
import vn.loitp.restapi.restclient.RestClientV2;
import vn.loitp.restapi.uiza.UizaService;
import vn.loitp.restapi.uiza.model.v2.auth.Auth;
import vn.loitp.restapi.uiza.model.v2.getlinkplay.GetLinkPlay;
import vn.loitp.restapi.uiza.model.v2.getlinkplay.Mpd;
import vn.loitp.restapi.uiza.model.v2.getplayerinfo.PlayerConfig;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.views.progressloadingview.avloadingindicatorview.lib.avi.AVLoadingIndicatorView;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */
//TODO remove debug_text_view, controls_root, retry_button
public class FrmTop extends BaseFragment implements View.OnClickListener, Player.EventListener, PlaybackControlView.VisibilityListener, ImaAdsMediaSource.AdsListener {
    private final String TAG = getClass().getSimpleName();
    public static final String ACTION_VIEW = "com.google.android.exoplayer.demo.action.VIEW";
    public static final String ACTION_VIEW_LIST = "com.google.android.exoplayer.demo.action.VIEW_LIST";

    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private static final CookieManager DEFAULT_COOKIE_MANAGER;

    static {
        DEFAULT_COOKIE_MANAGER = new CookieManager();
        DEFAULT_COOKIE_MANAGER.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    private Handler mainHandler;
    private EventLogger eventLogger;
    private SimpleExoPlayerView simpleExoPlayerView;
    private LinearLayout debugRootView;
    private TextView debugTextView;
    private Button retryButton;

    private DataSource.Factory mediaDataSourceFactory;
    private SimpleExoPlayer player;
    private DefaultTrackSelector trackSelector;
    private TrackSelectionHelper trackSelectionHelper;
    private DebugTextViewHelper debugViewHelper;
    private boolean inErrorState;
    private TrackGroupArray lastSeenTrackGroupArray;

    private boolean shouldAutoPlay;
    private int resumeWindow;
    private long resumePosition;

    // Fields used only for ad playback. The ads loader is loaded via reflection.
    private Object imaAdsLoader; // ImaAdsLoader
    private Uri loadedAdTagUri;
    private ViewGroup adOverlayViewGroup;

    private FrameLayout rootView;

    private AVLoadingIndicatorView avi;
    //TODO remove gson later
    private Gson gson = new Gson();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_top, container, false);
        shouldAutoPlay = true;
        clearResumePosition();
        mediaDataSourceFactory = buildDataSourceFactory(true);
        mainHandler = new Handler();
        if (CookieHandler.getDefault() != DEFAULT_COOKIE_MANAGER) {
            CookieHandler.setDefault(DEFAULT_COOKIE_MANAGER);
        }

        avi = (AVLoadingIndicatorView) view.findViewById(io.uiza.sdk.ui.R.id.avi);
        avi.smoothToShow();
        rootView = (FrameLayout) view.findViewById(io.uiza.sdk.ui.R.id.root_view);
        rootView.setOnClickListener(this);

        debugRootView = (LinearLayout) view.findViewById(io.uiza.sdk.ui.R.id.controls_root);
        debugTextView = (TextView) view.findViewById(io.uiza.sdk.ui.R.id.debug_text_view);
        retryButton = (Button) view.findViewById(io.uiza.sdk.ui.R.id.retry_button);
        retryButton.setOnClickListener(this);

        simpleExoPlayerView = (SimpleExoPlayerView) view.findViewById(io.uiza.sdk.ui.R.id.player_view);
        simpleExoPlayerView.setControllerVisibilityListener(this);
        simpleExoPlayerView.requestFocus();

        //simpleExoPlayerView.setUseArtwork(true);
        //Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic);
        //simpleExoPlayerView.setDefaultArtwork(bm);

        simpleExoPlayerView.setCallback(new SimpleExoPlayerView.Callback() {
            @Override
            public void onClickSetting() {
                LLog.d(TAG, "onClickSetting");
                if (getBtVideo() != null) {
                    getBtVideo().performClick();
                }
            }

            @Override
            public void onPlayThrough(int percent) {
                //TODO
            }
        });

        simpleExoPlayerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                //LLog.d(TAG, "onLayoutChange " + bottom);
                simpleExoPlayerView.getController().setSizeOfPlaybackControlView();
                UizaData.getInstance().setSizeHeightOfSimpleExoPlayerView(bottom);
            }
        });

        return view;
    }

    private View getBtVideo() {
        for (int i = 0; i < debugRootView.getChildCount(); i++) {
            View childView = debugRootView.getChildAt(i);
            if (childView instanceof Button) {
                if (((Button) childView).getText().toString().equalsIgnoreCase(getString(io.uiza.sdk.ui.R.string.video))) {
                    return childView;
                }
            }
        }
        return null;
    }

    private View getBtAudio() {
        for (int i = 0; i < debugRootView.getChildCount(); i++) {
            View childView = debugRootView.getChildAt(i);
            if (childView instanceof Button) {
                if (((Button) childView).getText().toString().equalsIgnoreCase(getString(io.uiza.sdk.ui.R.string.audio))) {
                    return childView;
                }
            }
        }
        return null;
    }

    private View getBtText() {
        for (int i = 0; i < debugRootView.getChildCount(); i++) {
            View childView = debugRootView.getChildAt(i);
            if (childView instanceof Button) {
                if (((Button) childView).getText().toString().equalsIgnoreCase(getString(io.uiza.sdk.ui.R.string.text))) {
                    return childView;
                }
            }
        }
        return null;
    }


    @Override
    public void onVisibilityChange(int visibility) {
        //LLog.d(TAG, "onVisibilityChange " + visibility);
        debugRootView.setVisibility(visibility);
        if (visibilityChange != null) {
            visibilityChange.onVisibilityChange(visibility);
        }
    }

    public interface VisibilityChange {
        public void onVisibilityChange(int visibility);
    }

    private VisibilityChange visibilityChange;

    public void setVisibilityChange(VisibilityChange visibilityChange) {
        this.visibilityChange = visibilityChange;
    }

    private InputModel inputModel;

    public void setInputModel(InputModel ip, boolean reloadData) {
        if (ip == null) {
            this.inputModel = UizaData.getInstance().getInputModel();
        } else {
            this.inputModel = ip;
        }
        if (reloadData) {
            releasePlayer();
            shouldAutoPlay = true;
            clearResumePosition();

            initializePlayer();
        }
    }

    public void initializePlayer() {
        if (inputModel == null) {
            //ToastUtils.showShort("initializePlayer inputModel == null return");
            LLog.d(TAG, "initializePlayer inputModel == null return");
            return;
        }
        if (inputModel.getUri() == null) {
            LLog.d(TAG, "inputModel.getUri() == null -> return");
            return;
        }
        /*if (inputModel.getDetailEntity() == null) {
            LLog.d(TAG, "inputModel.getDetailEntity() == null -> return");
            return;
        }*/

        boolean needNewPlayer = player == null;
        if (needNewPlayer) {
            TrackSelection.Factory adaptiveTrackSelectionFactory = new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
            trackSelector = new DefaultTrackSelector(adaptiveTrackSelectionFactory);
            trackSelectionHelper = new TrackSelectionHelper(trackSelector, adaptiveTrackSelectionFactory);
            lastSeenTrackGroupArray = null;
            eventLogger = new EventLogger(trackSelector);

            UUID drmSchemeUuid = inputModel.getDrmSchemeUuid();
            DrmSessionManager<FrameworkMediaCrypto> drmSessionManager = null;
            if (drmSchemeUuid != null) {
                String drmLicenseUrl = inputModel.getDrmLicenseUrl();
                String[] keyRequestPropertiesArray = inputModel.getKeyRequestPropertiesArray();
                int errorStringId = io.uiza.sdk.ui.R.string.error_drm_unknown;
                if (Util.SDK_INT < 18) {
                    errorStringId = io.uiza.sdk.ui.R.string.error_drm_not_supported;
                } else {
                    try {
                        drmSessionManager = buildDrmSessionManagerV18(drmSchemeUuid, drmLicenseUrl, keyRequestPropertiesArray);
                    } catch (UnsupportedDrmException e) {
                        errorStringId = e.reason == UnsupportedDrmException.REASON_UNSUPPORTED_SCHEME ? io.uiza.sdk.ui.R.string.error_drm_unsupported_scheme : io.uiza.sdk.ui.R.string.error_drm_unknown;
                    }
                }
                if (drmSessionManager == null) {
                    showToast(errorStringId);
                    return;
                }
            }

            boolean preferExtensionDecoders = inputModel.getPreferExtensionDecoders();
            /*@DefaultRenderersFactory.ExtensionRendererMode int extensionRendererMode =
                    ((LSApplication) ((Activity) getContext()).getApplication()).useExtensionRenderers()
                            ? (preferExtensionDecoders ? DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER
                            : DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON)
                            : DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF;*/
            @DefaultRenderersFactory.ExtensionRendererMode int extensionRendererMode = useExtensionRenderers()
                    ? (preferExtensionDecoders ? DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER
                    : DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON)
                    : DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF;
            DefaultRenderersFactory renderersFactory = new DefaultRenderersFactory(getContext(), drmSessionManager, extensionRendererMode);

            player = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector);
            player.addListener(this);
            player.addListener(eventLogger);
            player.addMetadataOutput(eventLogger);
            player.setAudioDebugListener(eventLogger);
            player.setVideoDebugListener(eventLogger);
            player.setRepeatMode(Player.REPEAT_MODE_ALL);

            simpleExoPlayerView.setPlayer(player);
            player.setPlayWhenReady(shouldAutoPlay);
            debugViewHelper = new DebugTextViewHelper(player, debugTextView);
            debugViewHelper.start();
        }
        String action = inputModel.getAction();
        Uri[] uris;
        String[] extensions;
        if (ACTION_VIEW.equals(action)) {
            uris = new Uri[]{inputModel.getUri()};
            //LLog.d("uris ", ">>>uris: " + LSApplication.getInstance().getGson().toJson(uris));
            extensions = new String[]{inputModel.getExtension()};
        } else if (ACTION_VIEW_LIST.equals(action)) {
            String[] uriStrings = inputModel.getUriStrings();
            uris = new Uri[uriStrings.length];
            for (int i = 0; i < uriStrings.length; i++) {
                uris[i] = Uri.parse(uriStrings[i]);
            }
            extensions = inputModel.getExtensionList();
            if (extensions == null) {
                extensions = new String[uriStrings.length];
            }
        } else {
            showToast(getContext().getString(io.uiza.sdk.ui.R.string.unexpected_intent_action, action));
            return;
        }
        if (Util.maybeRequestReadExternalStoragePermission((Activity) getContext(), uris)) {
            // The player will be reinitialized if the permission is granted.
            return;
        }
        MediaSource[] mediaSources = new MediaSource[uris.length];
        for (int i = 0; i < uris.length; i++) {
            mediaSources[i] = buildMediaSource(uris[i], extensions[i]);
        }
        MediaSource mediaSource = mediaSources.length == 1 ? mediaSources[0] : new ConcatenatingMediaSource(mediaSources);
        String adTagUriString = inputModel.getAdTagUri();
        LLog.d(TAG, "adTagUriString " + adTagUriString);
        if (adTagUriString != null) {
            Uri adTagUri = Uri.parse(adTagUriString);
            if (!adTagUri.equals(loadedAdTagUri)) {
                releaseAdsLoader();
                loadedAdTagUri = adTagUri;
            }
            try {
                mediaSource = createAdsMediaSource(mediaSource, Uri.parse(adTagUriString));
            } catch (Exception e) {
                showToast(io.uiza.sdk.ui.R.string.ima_not_loaded);
            }
        } else {
            releaseAdsLoader();
        }
        boolean haveResumePosition = resumeWindow != C.INDEX_UNSET;
        if (haveResumePosition) {
            player.seekTo(resumeWindow, resumePosition);
        }

        //TODO with subtitle vtt
        /*Format textFormat = Format.createTextSampleFormat(null, MimeTypes.TEXT_VTT, Format.NO_VALUE, "en", null);
        String urlSubtitle = "https://s3-ap-southeast-1.amazonaws.com/58aa3a0eb555420a945a27b47ce9ef2f-data/static/type_caption__entityId_81__language_en.vtt";
        MediaSource textMediaSource = new SingleSampleMediaSource(Uri.parse(urlSubtitle)
                , mediaDataSourceFactory
                , textFormat
                , C.TIME_UNSET);
        MediaSource mediaSourceWithText = new MergingMediaSource(mediaSource, textMediaSource);
        player.prepare(mediaSourceWithText, !haveResumePosition, false);*/

        player.prepare(mediaSource, !haveResumePosition, false);
        inErrorState = false;
        updateButtonVisibilities();

        mPlayerConfig = UizaData.getInstance().getPlayerConfig();
        setConfigUIPlayer();
    }

    private PlayerConfig mPlayerConfig;

    private void setConfigUIPlayer() {
        //freuss47 customize UI
        PlaybackControlView playbackControlView = getPlayerView().getController();
        if (playbackControlView == null) {
            return;
        }
        playbackControlView.setTitle(inputModel.getTitle());

        playbackControlView.setVisibilityFullscreenButton(mPlayerConfig.getSetting().getAllowFullscreen().equals(UizaData.T));
        playbackControlView.setVisibilityShowQuality(mPlayerConfig.getSetting().getShowQuality().equals(UizaData.T));
        playbackControlView.setVisibilityDisplayPlaylist(mPlayerConfig.getSetting().getDisplayPlaylist().equals(UizaData.T));

        if (mPlayerConfig.getSetting().getAutoStart().equals(UizaData.T)) {
            shouldAutoPlay = true;
            simpleExoPlayerView.getPlayer().setPlayWhenReady(shouldAutoPlay);
        } else {
            shouldAutoPlay = false;
            simpleExoPlayerView.getPlayer().setPlayWhenReady(shouldAutoPlay);
        }
        //set icon color
        try {
            int color = Color.parseColor(mPlayerConfig.getStyling().getIcons());
            playbackControlView.setColorAllIcons(color);
        } catch (Exception e) {
            LLog.e(TAG, "setConfigUIPlayer Color.parseColor setColorAllIcons " + e.toString());
        }

        //set buffer color
        try {
            int color = Color.parseColor(mPlayerConfig.getStyling().getBuffer());
            playbackControlView.setBufferColor(color);
        } catch (Exception e) {
            LLog.e(TAG, "setConfigUIPlayer Color.parseColor setBufferColor " + e.toString());
        }

        //set progress color
        try {
            int color = Color.parseColor(mPlayerConfig.getStyling().getProgress());
            playbackControlView.setProgressColor(color);
        } catch (Exception e) {
            LLog.e(TAG, "setConfigUIPlayer Color.parseColor setProgressColor " + e.toString());
        }

        //TODO set background???
        //mPlayerConfig.getStyling().getBackground()

        //TODO show hide title
        /*try {
            playbackControlView.showOrHideTitleView(false);
            //playbackControlView.showOrHideTitleView(mPlayerConfig.getStyling().getTitle().equals(UizaData.T));
        } catch (NullPointerException e) {
            LLog.e(TAG, "setConfigUIPlayer NullPointerException " + e.toString());
        }*/
    }

    private MediaSource buildMediaSource(Uri uri, String overrideExtension) {
        int type = TextUtils.isEmpty(overrideExtension) ? Util.inferContentType(uri) : Util.inferContentType("." + overrideExtension);
        switch (type) {
            case C.TYPE_SS:
                return new SsMediaSource(uri, buildDataSourceFactory(false), new DefaultSsChunkSource.Factory(mediaDataSourceFactory), mainHandler, eventLogger);
            case C.TYPE_DASH:
                return new DashMediaSource(uri, buildDataSourceFactory(false), new DefaultDashChunkSource.Factory(mediaDataSourceFactory), mainHandler, eventLogger);
            case C.TYPE_HLS:
                return new HlsMediaSource(uri, mediaDataSourceFactory, mainHandler, eventLogger);
            case C.TYPE_OTHER:
                return new ExtractorMediaSource(uri, mediaDataSourceFactory, new DefaultExtractorsFactory(), mainHandler, eventLogger);
            default: {
                throw new IllegalStateException("Unsupported type: " + type);
            }
        }
    }

    private DrmSessionManager<FrameworkMediaCrypto> buildDrmSessionManagerV18(UUID uuid, String licenseUrl, String[] keyRequestPropertiesArray) throws UnsupportedDrmException {
        HttpMediaDrmCallback drmCallback = new HttpMediaDrmCallback(licenseUrl, buildHttpDataSourceFactory(false));
        if (keyRequestPropertiesArray != null) {
            for (int i = 0; i < keyRequestPropertiesArray.length - 1; i += 2) {
                drmCallback.setKeyRequestProperty(keyRequestPropertiesArray[i], keyRequestPropertiesArray[i + 1]);
            }
        }
        return new DefaultDrmSessionManager<>(uuid, FrameworkMediaDrm.newInstance(uuid), drmCallback, null, mainHandler, eventLogger);
    }

    public void releasePlayer() {
        if (player != null) {
            debugViewHelper.stop();
            debugViewHelper = null;
            shouldAutoPlay = player.getPlayWhenReady();
            updateResumePosition();
            player.release();
            player = null;
            trackSelector = null;
            trackSelectionHelper = null;
            eventLogger = null;
        }
    }

    private void updateResumePosition() {
        resumeWindow = player.getCurrentWindowIndex();
        resumePosition = Math.max(0, player.getContentPosition());
    }

    private void clearResumePosition() {
        resumeWindow = C.INDEX_UNSET;
        resumePosition = C.TIME_UNSET;
    }

    /**
     * Returns a new DataSource factory.
     *
     * @param useBandwidthMeter Whether to set {@link #BANDWIDTH_METER} as a listener to the new
     *                          DataSource factory.
     * @return A new DataSource factory.
     */


    private DataSource.Factory buildDataSourceFactory(boolean useBandwidthMeter) {
        //return ((LSApplication) ((Activity) getContext()).getApplication()).buildDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
        return buildDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
    }

    //freuss47 set userAgent
    private String userAgent = "ExoPlayerDemoUiza";

    private DataSource.Factory buildDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultDataSourceFactory(getContext(), bandwidthMeter, buildHttpDataSourceFactory(bandwidthMeter));
    }

    private HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultHttpDataSourceFactory(userAgent, bandwidthMeter);
    }

    private boolean useExtensionRenderers() {
        return BuildConfig.FLAVOR.equals("withExtensions");
    }

    /**
     * Returns a new HttpDataSource factory.
     *
     * @param useBandwidthMeter Whether to set {@link #BANDWIDTH_METER} as a listener to the new
     *                          DataSource factory.
     * @return A new HttpDataSource factory.
     */
    private HttpDataSource.Factory buildHttpDataSourceFactory(boolean useBandwidthMeter) {
        //return ((LSApplication) ((Activity) getContext()).getApplication()).buildHttpDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
        return buildHttpDataSourceFactory(useBandwidthMeter ? BANDWIDTH_METER : null);
    }

    /**
     * Returns an ads media source, reusing the ads loader if one exists.
     *
     * @throws Exception Thrown if it was not possible to create an ads media source, for example, due
     *                   to a missing dependency.
     */
    private MediaSource createAdsMediaSource(MediaSource mediaSource, Uri adTagUri) throws Exception {
        // Load the extension source using reflection so the demo app doesn't have to depend on it.
        // The ads loader is reused for multiple playbacks, so that ad playback can resume.

        /*Class<?> loaderClass = Class.forName("com.google.android.exoplayer2.ext.ima.ImaAdsLoader");
        if (imaAdsLoader == null) {
            imaAdsLoader = loaderClass.getConstructor(Context.class, Uri.class).newInstance(this, adTagUri);
            adOverlayViewGroup = new FrameLayout(getContext());
            // The demo app has a non-null overlay frame layout.
            simpleExoPlayerView.getOverlayFrameLayout().addView(adOverlayViewGroup);
        }
        Class<?> sourceClass = Class.forName("com.google.android.exoplayer2.ext.ima.ImaAdsMediaSource");
        Constructor<?> constructor = sourceClass.getConstructor(MediaSource.class, DataSource.Factory.class, loaderClass, ViewGroup.class);
        return (MediaSource) constructor.newInstance(mediaSource, mediaDataSourceFactory, imaAdsLoader, adOverlayViewGroup);*/

        ImaAdsLoader imaAdsLoader = new ImaAdsLoader(getContext(), adTagUri);
        adOverlayViewGroup = new FrameLayout(getContext());
        simpleExoPlayerView.getOverlayFrameLayout().addView(adOverlayViewGroup);
        //ImaAdsMediaSource imaAdsMediaSource = new ImaAdsMediaSource(mediaSource, mediaDataSourceFactory, imaAdsLoader, adOverlayViewGroup);
        ImaAdsMediaSource imaAdsMediaSource = new ImaAdsMediaSource(mediaSource, mediaDataSourceFactory, imaAdsLoader, adOverlayViewGroup, new Handler(), this);
        return imaAdsMediaSource;
    }

    private void releaseAdsLoader() {
        if (imaAdsLoader != null) {
            try {
                Class<?> loaderClass = Class.forName("com.uiza.player.ext.ima.ImaAdsLoader");
                Method releaseMethod = loaderClass.getMethod("release");
                releaseMethod.invoke(imaAdsLoader);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            imaAdsLoader = null;
            loadedAdTagUri = null;
            simpleExoPlayerView.getOverlayFrameLayout().removeAllViews();
        }
    }

    // Player.EventListener implementation
    @Override
    public void onLoadingChanged(boolean isLoading) {
        // Do nothing.
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        //TODO onPlayerStateChanged
        if (playbackState == Player.STATE_ENDED) {
            LLog.d(TAG, "STATE_ENDED");
            avi.smoothToHide();
            showControls();
        } else if (playbackState == Player.STATE_BUFFERING) {
            LLog.d(TAG, "STATE_BUFFERING");
            avi.smoothToShow();
        } else if (playbackState == Player.STATE_IDLE) {
            LLog.d(TAG, "STATE_IDLE");
            avi.smoothToShow();
        } else if (playbackState == Player.STATE_READY) {
            LLog.d(TAG, "STATE_READY");
            avi.smoothToHide();
        }
        updateButtonVisibilities();
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {
        // Do nothing.
    }

    @Override
    public void onPositionDiscontinuity() {
        if (inErrorState) {
            // This will only occur if the user has performed a seek whilst in the error state. Update the
            // resume position so that if the user then retries, playback will resume from the position to
            // which they seeked.
            updateResumePosition();
        }
    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        // Do nothing.
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {
        // Do nothing.
    }

    @Override
    public void onPlayerError(ExoPlaybackException e) {
        String errorString = null;
        if (e.type == ExoPlaybackException.TYPE_RENDERER) {
            Exception cause = e.getRendererException();
            if (cause instanceof MediaCodecRenderer.DecoderInitializationException) {
                // Special case for decoder initialization failures.
                MediaCodecRenderer.DecoderInitializationException decoderInitializationException = (MediaCodecRenderer.DecoderInitializationException) cause;
                if (decoderInitializationException.decoderName == null) {
                    if (decoderInitializationException.getCause() instanceof MediaCodecUtil.DecoderQueryException) {
                        errorString = getContext().getString(io.uiza.sdk.ui.R.string.error_querying_decoders);
                    } else if (decoderInitializationException.secureDecoderRequired) {
                        errorString = getContext().getString(io.uiza.sdk.ui.R.string.error_no_secure_decoder, decoderInitializationException.mimeType);
                    } else {
                        errorString = getContext().getString(io.uiza.sdk.ui.R.string.error_no_decoder, decoderInitializationException.mimeType);
                    }
                } else {
                    errorString = getContext().getString(io.uiza.sdk.ui.R.string.error_instantiating_decoder, decoderInitializationException.decoderName);
                }
            }
        }
        if (errorString != null) {
            showToast(errorString);
        }
        inErrorState = true;
        if (isBehindLiveWindow(e)) {
            clearResumePosition();
            initializePlayer();
        } else {
            updateResumePosition();
            updateButtonVisibilities();
            showControls();
        }
    }

    @Override
    @SuppressWarnings("ReferenceEquality")
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        updateButtonVisibilities();
        if (trackGroups != lastSeenTrackGroupArray) {
            MappingTrackSelector.MappedTrackInfo mappedTrackInfo = trackSelector.getCurrentMappedTrackInfo();
            if (mappedTrackInfo != null) {
                if (mappedTrackInfo.getTrackTypeRendererSupport(C.TRACK_TYPE_VIDEO) == MappingTrackSelector.MappedTrackInfo.RENDERER_SUPPORT_UNSUPPORTED_TRACKS) {
                    showToast(io.uiza.sdk.ui.R.string.error_unsupported_video);
                }
                if (mappedTrackInfo.getTrackTypeRendererSupport(C.TRACK_TYPE_AUDIO) == MappingTrackSelector.MappedTrackInfo.RENDERER_SUPPORT_UNSUPPORTED_TRACKS) {
                    showToast(io.uiza.sdk.ui.R.string.error_unsupported_audio);
                }
            }
            lastSeenTrackGroupArray = trackGroups;
        }
    }

    // User controls
    private void updateButtonVisibilities() {
        debugRootView.removeAllViews();

        retryButton.setVisibility(inErrorState ? View.VISIBLE : View.GONE);
        debugRootView.addView(retryButton);

        if (player == null) {
            return;
        }

        MappingTrackSelector.MappedTrackInfo mappedTrackInfo = trackSelector.getCurrentMappedTrackInfo();
        if (mappedTrackInfo == null) {
            return;
        }


        for (int i = 0; i < mappedTrackInfo.length; i++) {
            TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(i);
            if (trackGroups.length != 0) {
                Button button = new Button(getActivity());
                int label;
                switch (player.getRendererType(i)) {
                    case C.TRACK_TYPE_AUDIO:
                        label = io.uiza.sdk.ui.R.string.audio;
                        break;
                    case C.TRACK_TYPE_VIDEO:
                        label = io.uiza.sdk.ui.R.string.video;
                        break;
                    case C.TRACK_TYPE_TEXT:
                        label = io.uiza.sdk.ui.R.string.text;
                        break;
                    default:
                        continue;
                }
                button.setText(label);
                button.setTag(i);
                button.setOnClickListener(this);
                debugRootView.addView(button, debugRootView.getChildCount() - 1);
                //LLog.d(TAG, "updateButtonVisibilities addView " + button.getText().toString() + ", tag: " + button.getTag().toString());
            }
        }

        /*LUIUtil.setDelay(5000, new LUIUtil.DelayCallback() {
            @Override
            public void doAfter(int mls) {
                if (bttest == null) {
                    LLog.d(TAG, "updateButtonVisibilities doAfter 5000mls button==null");
                } else {
                    LLog.d(TAG, "updateButtonVisibilities doAfter 5000mls performClick");
                    bttest.performClick();
                }
            }
        });*/
    }

    private void showControls() {
        debugRootView.setVisibility(View.VISIBLE);
    }

    private void showToast(int messageId) {
        showToast(getContext().getString(messageId));
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private static boolean isBehindLiveWindow(ExoPlaybackException e) {
        if (e.type != ExoPlaybackException.TYPE_SOURCE) {
            return false;
        }
        Throwable cause = e.getSourceException();
        while (cause != null) {
            if (cause instanceof BehindLiveWindowException) {
                return true;
            }
            cause = cause.getCause();
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view == retryButton) {
            initializePlayer();
        } else if (view.getParent() == debugRootView) {
            LLog.d(TAG, "onClick " + ((Button) view).getText());
            MappingTrackSelector.MappedTrackInfo mappedTrackInfo = trackSelector.getCurrentMappedTrackInfo();
            if (mappedTrackInfo != null) {
                trackSelectionHelper.showSelectionDialog((Activity) getContext(), ((Button) view).getText(), mappedTrackInfo, (int) view.getTag(), new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (simpleExoPlayerView != null) {
                            simpleExoPlayerView.resumePlayVideo();
                        }
                        if (UizaData.getInstance().isLandscape()) {
                            UizaScreenUtil.updateUIStatusNavigationBar(getActivity(), false);
                        } else {
                            //do nothing
                        }
                        simpleExoPlayerView.hideAllOtherControlView();
                        simpleExoPlayerView.hideController();
                    }
                });
            }
        }
    }

    /*public void onNewIntent(Intent intent) {
        releasePlayer();
        shouldAutoPlay = true;
        clearResumePosition();
        ((Activity) getContext()).setIntent(intent);
    }*/

    /*public void onStartUizaVideo() {
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }*/

    @Override
    public void onStart() {
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
        super.onStart();
        EventBus.getDefault().register(this);
    }

    /*public void onResumeUizaVideo() {
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }*/

    @Override
    public void onResume() {
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
        super.onResume();
    }

    /*public void onPauseUizaVideo() {
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }*/

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            UizaData.getInstance().setCurrentPosition(player.getCurrentPosition());
        }
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    /*public void onStopUizaVideo() {
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }*/

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
        EventBus.getDefault().unregister(this);
    }

    /*public void onDestroyUizaVideo() {
        releaseAdsLoader();
    }*/

    @Override
    public void onDestroy() {
        releaseAdsLoader();
        super.onDestroy();
    }

    public SimpleExoPlayerView getPlayerView() {
        return simpleExoPlayerView;
    }

    public void seekTo(final long position) {
        if (player != null) {
            //LLog.d(TAG, "player != null");
            player.seekTo(position);
        } else {
            //LLog.d(TAG, "player == null");
            UizaUIUtil.setDelay(200, new UizaUIUtil.DelayCallback() {
                @Override
                public void doAfter(int mls) {
                    seekTo(position);
                }
            });
        }
    }

    @Override
    public void onAdLoadError(IOException error) {
        LLog.d(TAG, "onAdLoadError");
    }

    @Override
    public void onAdClicked() {
        //TODO onAdClicked
        LLog.d(TAG, "onAdClicked");
        showToast("onAdClicked");
    }

    @Override
    public void onAdTapped() {
        //TODO onAdTapped
        LLog.d(TAG, "onAdTapped");
        showToast("onAdTapped");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventBusData.ClickVideoEvent clickVideoEvent) {
        LLog.d(TAG, TAG + " clickVideoEvent");
        if (clickVideoEvent != null) {
            if (simpleExoPlayerView != null) {
                LLog.d(TAG, "clickVideoEvent if");
                shouldAutoPlay = true;
                getLinkPlay(clickVideoEvent.getEntityId());
            } else {
                LLog.d(TAG, "clickVideoEvent else");
            }
        }
    }

    private void getLinkPlay(String entityId) {
        UizaService service = RestClientV2.createService(UizaService.class);
        Auth auth = LPref.getAuth(getActivity(), gson);
        if (auth == null || auth.getAppId() == null) {
            showDialogError("Error auth == null || auth.getAppId() == null");
            return;
        }
        //LLog.d(TAG, ">>>getLinkPlay appId: " + auth.getAppId());

        //API v1
        /*subscribe(service.getLinkPlay(inputModel.getEntityID(), auth.getAppId()), new ApiSubscriber<GetLinkPlay>() {
            @Override
            public void onSuccess(GetLinkPlay getLinkPlay) {
                LLog.d(TAG, "getLinkPlay onSuccess " + gson.toJson(getLinkPlay));
                //UizaData.getInstance().setLinkPlay("http://demos.webmproject.org/dash/201410/vp9_glass/manifest_vp9_opus.mpd");
                //UizaData.getInstance().setLinkPlay("http://dev-preview.uiza.io/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxNjMzMjU0NSwiZXhwIjoxNTE2NDE4OTQ1LCJlbnRpdHlfaWQiOiIzYWUwOWJhNC1jMmJmLTQ3MjQtYWRmNC03OThmMGFkZDY1MjAiLCJlbnRpdHlfbmFtZSI6InRydW5nbnQwMV8xMiIsImVudGl0eV9zdHJlYW1fdHlwZSI6InZvZCIsImFwcF9pZCI6ImEyMDRlOWNkZWNhNDQ5NDhhMzNlMGQwMTJlZjc0ZTkwIiwic3ViIjoiYTIwNGU5Y2RlY2E0NDk0OGEzM2UwZDAxMmVmNzRlOTAifQ.ktZsaoGA3Dp4J1cGR00bt4UIiMtcsjxgzJWSTnxnxKk/a204e9cdeca44948a33e0d012ef74e90-data/transcode-output/unzKBIUm/package/playlist.mpd");

                //TODO play link mpd0 (pre cdn la vn) ko dc thi play link mpd1(cdn la QT)
                LLog.d(TAG, "getLinkplayMpd " + getLinkPlay.getLinkplayMpd());
                UizaData.getInstance().setLinkPlay(getLinkPlay.getLinkplayMpd());
                isGetLinkPlayDone = true;
                init();
            }

            @Override
            public void onFail(Throwable e) {
                LLog.d(TAG, "onFail " + e.toString());
                handleException(e);
            }
        });*/
        //End API v1

        //API v2
        subscribe(service.getLinkPlayV2(entityId, auth.getAppId()), new ApiSubscriber<GetLinkPlay>() {
            @Override
            public void onSuccess(vn.loitp.restapi.uiza.model.v2.getlinkplay.GetLinkPlay getLinkPlay) {
                //LLog.d(TAG, "getLinkPlay onSuccess " + gson.toJson(getLinkPlay));
                //UizaData.getInstance().setLinkPlay("http://demos.webmproject.org/dash/201410/vp9_glass/manifest_vp9_opus.mpd");
                //UizaData.getInstance().setLinkPlay("http://dev-preview.uiza.io/eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJVSVpBIiwiYXVkIjoidWl6YS5pbyIsImlhdCI6MTUxNjMzMjU0NSwiZXhwIjoxNTE2NDE4OTQ1LCJlbnRpdHlfaWQiOiIzYWUwOWJhNC1jMmJmLTQ3MjQtYWRmNC03OThmMGFkZDY1MjAiLCJlbnRpdHlfbmFtZSI6InRydW5nbnQwMV8xMiIsImVudGl0eV9zdHJlYW1fdHlwZSI6InZvZCIsImFwcF9pZCI6ImEyMDRlOWNkZWNhNDQ5NDhhMzNlMGQwMTJlZjc0ZTkwIiwic3ViIjoiYTIwNGU5Y2RlY2E0NDk0OGEzM2UwZDAxMmVmNzRlOTAifQ.ktZsaoGA3Dp4J1cGR00bt4UIiMtcsjxgzJWSTnxnxKk/a204e9cdeca44948a33e0d012ef74e90-data/transcode-output/unzKBIUm/package/playlist.mpd");

                try {
                    //Mpd mpdVN = getLinkPlay.getMpd().get(0);
                    Mpd mpdInter = getLinkPlay.getMpd().get(1);

                    String linkPlay = mpdInter.getUrl();
                    //LLog.d(TAG, "linkPlay " + linkPlay);

                    UizaData.getInstance().setLinkPlay(linkPlay);
                    setInputModel(null, true);
                } catch (NullPointerException e) {
                    LLog.e(TAG, "getLinkPlayV2 NullPointerException " + e.toString());
                    //showDialogError("getLinkPlayV2 Error NullPointerException " + e.toString());
                }
            }

            @Override
            public void onFail(Throwable e) {
                //LLog.d(TAG, "onFail " + e.toString());
                handleException(e);
            }
        });
        //End API v2
    }
}