/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uiza.player.ui.views.helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckedTextView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.FixedTrackSelection;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector.MappedTrackInfo;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector.SelectionOverride;
import com.google.android.exoplayer2.trackselection.RandomTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.uiza.player.ui.data.UizaData;

import java.util.Arrays;

import io.uiza.sdk.ui.R;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LScreenUtil;

/**
 * Helper class for displaying track selection dialogs.
 */
/* package */public final class TrackSelectionHelper implements View.OnClickListener, DialogInterface.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private static final TrackSelection.Factory FIXED_FACTORY = new FixedTrackSelection.Factory();
    private static final TrackSelection.Factory RANDOM_FACTORY = new RandomTrackSelection.Factory();

    private final MappingTrackSelector selector;
    private final TrackSelection.Factory adaptiveTrackSelectionFactory;

    private MappedTrackInfo trackInfo;
    private int rendererIndex;
    private TrackGroupArray trackGroups;
    private boolean[] trackGroupsAdaptive;
    private boolean isDisabled;
    private SelectionOverride override;

    private CheckedTextView disableView;
    private CheckedTextView defaultView;
    private CheckedTextView enableRandomAdaptationView;
    private CheckedTextView[][] trackViews;

    /**
     * @param selector                      The track selector.
     * @param adaptiveTrackSelectionFactory A factory for adaptive {@link TrackSelection}s, or null
     *                                      if the selection helper should not support adaptive tracks.
     */
    public TrackSelectionHelper(MappingTrackSelector selector, TrackSelection.Factory adaptiveTrackSelectionFactory) {
        this.selector = selector;
        this.adaptiveTrackSelectionFactory = adaptiveTrackSelectionFactory;
    }

    /**
     * Shows the selection dialog for a given renderer.
     *
     * @param activity      The parent activity.
     * @param title         The dialog's title.
     * @param trackInfo     The current track information.
     * @param rendererIndex The index of the renderer.
     */

    public void showSelectionDialog(final Activity activity, CharSequence title, MappedTrackInfo trackInfo, int rendererIndex, final DialogInterface.OnDismissListener callbackDialogDissmiss) {
        this.trackInfo = trackInfo;
        this.rendererIndex = rendererIndex;

        trackGroups = trackInfo.getTrackGroups(rendererIndex);
        trackGroupsAdaptive = new boolean[trackGroups.length];
        for (int i = 0; i < trackGroups.length; i++) {
            trackGroupsAdaptive[i] = adaptiveTrackSelectionFactory != null
                    && trackInfo.getAdaptiveSupport(rendererIndex, i, false)
                    != RendererCapabilities.ADAPTIVE_NOT_SUPPORTED
                    && trackGroups.get(i).length > 1;
        }
        isDisabled = selector.getRendererDisabled(rendererIndex);
        LLog.d(TAG, "showSelectionDialog isDisabled " + isDisabled);
        override = selector.getSelectionOverride(rendererIndex, trackGroups);

        dialog = new Dialog(activity);
        final View view = buildView(activity);
        dialog.setContentView(view);
        dialog.setOnDismissListener(callbackDialogDissmiss);
        final Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            final WindowManager.LayoutParams param = window.getAttributes();
            param.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
            view.post(new Runnable() {
                @Override
                public void run() {
                    if (UizaData.getInstance().isVideoCanSlide()) {
                        //v2, video can slide
                        if (UizaData.getInstance().isLandscape()) {
                            LLog.d(TAG, "isLandscape");
                            LLog.d(TAG, "height size of dialog: " + view.getMeasuredHeight());
                            LLog.d(TAG, "param.y: " + (UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2));
                            param.y = UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2;
                            window.setAttributes(param);
                            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        } else {
                            LLog.d(TAG, "!isLandscape");
                            LLog.d(TAG, "height size of dialog: " + view.getMeasuredHeight());

                            int actionbarSizePx = LScreenUtil.getActionbarSizePx(activity);
                            LLog.d(TAG, "actionbarSizePx: " + actionbarSizePx);
                            LLog.d(TAG, "param.y: " + (UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2));
                            param.y = actionbarSizePx + UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2;
                            window.setAttributes(param);
                            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        }
                    } else {
                        //v1, no silde video
                        LLog.d(TAG, "isLandscape");
                        LLog.d(TAG, "height size of dialog: " + view.getMeasuredHeight());
                        LLog.d(TAG, "param.y: " + (UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2));
                        param.y = UizaData.getInstance().getSizeHeightOfSimpleExoPlayerView() / 2 - view.getMeasuredHeight() / 2;
                        window.setAttributes(param);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    }
                }
            });
        }
        dialog.show();
    }

    private Dialog dialog;

    public Dialog getDialog() {
        return dialog;
    }

    @SuppressLint("InflateParams")
    private View buildView(Context context) {
        LLog.d(TAG, "buildView");
        LayoutInflater inflater = LayoutInflater.from(context);
        //View view = inflater.inflate(R.layout.track_selection_dialog, null);
        //LinearLayout root = (LinearLayout) view.findViewById(R.id.root);
        LinearLayout root = new LinearLayout(context);
        root.setBackgroundColor(ContextCompat.getColor(context, R.color.black_65));
        //root.setOrientation(LinearLayout.HORIZONTAL);

        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
        root.addView(horizontalScrollView);

        LinearLayout subRoot = new LinearLayout(context);
        //subRoot.setBackgroundColor(Color.BLUE);
        //subRoot.setOrientation(LinearLayout.HORIZONTAL);

        horizontalScrollView.addView(subRoot);

        //TypedArray attributeArray = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.selectableItemBackground});
        //int selectableItemBackgroundResourceId = attributeArray.getResourceId(0, 0);
        //attributeArray.recycle();

        // View for disabling the renderer.
        disableView = (CheckedTextView) inflater.inflate(R.layout.view_setting_single_choice, root, false);
        //disableView.setBackgroundResource(selectableItemBackgroundResourceId);
        disableView.setText(R.string.selection_disabled);
        disableView.setFocusable(true);
        disableView.setTextColor(Color.WHITE);
        disableView.setCheckMarkDrawable(R.drawable.default_checkbox);
        disableView.setOnClickListener(this);

        subRoot.addView(disableView);

        // View for clearing the override to allow the selector to use its default selection logic.
        defaultView = (CheckedTextView) inflater.inflate(R.layout.view_setting_single_choice, root, false);
        //defaultView.setBackgroundResource(selectableItemBackgroundResourceId);
        defaultView.setText(R.string.selection_default);
        defaultView.setFocusable(true);
        defaultView.setOnClickListener(this);
        defaultView.setTextColor(Color.WHITE);
        defaultView.setCheckMarkDrawable(R.drawable.default_checkbox);
        //root.addView(inflater.inflate(R.layout.list_divider, root, false));
        subRoot.addView(defaultView);

        // Per-track views.
        boolean haveAdaptiveTracks = false;
        trackViews = new CheckedTextView[trackGroups.length][];
        for (int groupIndex = 0; groupIndex < trackGroups.length; groupIndex++) {
            TrackGroup group = trackGroups.get(groupIndex);
            boolean groupIsAdaptive = trackGroupsAdaptive[groupIndex];
            LLog.d(TAG, "groupIndex " + groupIndex + ",groupIsAdaptive " + groupIsAdaptive);
            haveAdaptiveTracks |= groupIsAdaptive;
            trackViews[groupIndex] = new CheckedTextView[group.length];
            for (int trackIndex = 0; trackIndex < group.length; trackIndex++) {
                //if (trackIndex == 0) {
                //    root.addView(inflater.inflate(R.layout.list_divider, root, false));
                //}
                int trackViewLayoutId = groupIsAdaptive ? R.layout.view_setting_mutiple_choice : R.layout.view_setting_single_choice;
                CheckedTextView trackView = (CheckedTextView) inflater.inflate(trackViewLayoutId, root, false);
                //trackView.setBackgroundResource(selectableItemBackgroundResourceId);
                LLog.d(TAG, "buildView: " + DemoUtil.buildTrackName(group.getFormat(trackIndex)));
                trackView.setText(DemoUtil.buildTrackName(group.getFormat(trackIndex)));
                trackView.setTextColor(Color.WHITE);
                trackView.setCheckMarkDrawable(R.drawable.default_checkbox);
                if (trackInfo.getTrackFormatSupport(rendererIndex, groupIndex, trackIndex) == RendererCapabilities.FORMAT_HANDLED) {
                    trackView.setFocusable(true);
                    trackView.setTag(Pair.create(groupIndex, trackIndex));
                    trackView.setOnClickListener(this);
                } else {
                    trackView.setFocusable(false);
                    trackView.setEnabled(false);
                }
                trackViews[groupIndex][trackIndex] = trackView;
                subRoot.addView(trackView);
            }
        }

        if (haveAdaptiveTracks) {
            // View for using random adaptation.
            enableRandomAdaptationView = (CheckedTextView) inflater.inflate(R.layout.view_setting_mutiple_choice, root, false);
            //enableRandomAdaptationView.setBackgroundResource(selectableItemBackgroundResourceId);
            enableRandomAdaptationView.setText(R.string.enable_random_adaptation);
            enableRandomAdaptationView.setOnClickListener(this);
            //root.addView(inflater.inflate(R.layout.list_divider, root, false));
            root.addView(enableRandomAdaptationView);
        }

        updateViews();
        return root;
    }

    private void updateViews() {
        disableView.setChecked(isDisabled);
        defaultView.setChecked(!isDisabled && override == null);
        for (int i = 0; i < trackViews.length; i++) {
            for (int j = 0; j < trackViews[i].length; j++) {
                trackViews[i][j].setChecked(override != null && override.groupIndex == i && override.containsTrack(j));
            }
        }
        if (enableRandomAdaptationView != null) {
            boolean enableView = !isDisabled && override != null && override.length > 1;
            enableRandomAdaptationView.setEnabled(enableView);
            enableRandomAdaptationView.setFocusable(enableView);
            if (enableView) {
                enableRandomAdaptationView.setChecked(!isDisabled && override.factory instanceof RandomTrackSelection.Factory);
            }
        }
    }

    // DialogInterface.OnClickListener
    @Override
    public void onClick(DialogInterface dialog, int which) {
        LLog.d(TAG, "onClick DialogInterface dialog");
        apply();
    }

    private void apply() {
        selector.setRendererDisabled(rendererIndex, isDisabled);
        if (override != null) {
            selector.setSelectionOverride(rendererIndex, trackGroups, override);
        } else {
            selector.clearSelectionOverrides(rendererIndex);
        }
        dissmissDialog();
    }

    public void dissmissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    // View.OnClickListener
    @Override
    public void onClick(View view) {
        if (view == disableView) {
            LLog.d(TAG, "onClick view == disableView");
            isDisabled = true;
            override = null;
        } else if (view == defaultView) {
            LLog.d(TAG, "onClick view == defaultView");
            isDisabled = false;
            override = null;
        } else if (view == enableRandomAdaptationView) {
            LLog.d(TAG, "onClick view == enableRandomAdaptationView");
            setOverride(override.groupIndex, override.tracks, !enableRandomAdaptationView.isChecked());
        } else {
            LLog.d(TAG, "onClick view else");
            isDisabled = false;
            @SuppressWarnings("unchecked")
            Pair<Integer, Integer> tag = (Pair<Integer, Integer>) view.getTag();
            int groupIndex = tag.first;
            int trackIndex = tag.second;
            if (!trackGroupsAdaptive[groupIndex] || override == null || override.groupIndex != groupIndex) {
                override = new SelectionOverride(FIXED_FACTORY, groupIndex, trackIndex);
            } else {
                // The group being modified is adaptive and we already have a non-null override.
                boolean isEnabled = ((CheckedTextView) view).isChecked();
                int overrideLength = override.length;
                if (isEnabled) {
                    // Remove the track from the override.
                    if (overrideLength == 1) {
                        // The last track is being removed, so the override becomes empty.
                        override = null;
                        isDisabled = true;
                    } else {
                        setOverride(groupIndex, getTracksRemoving(override, trackIndex), enableRandomAdaptationView.isChecked());
                    }
                } else {
                    // Add the track to the override.
                    setOverride(groupIndex, getTracksAdding(override, trackIndex), enableRandomAdaptationView.isChecked());
                }
            }
            apply();
        }
        // Update the views with the new state.
        updateViews();
    }

    private void setOverride(int group, int[] tracks, boolean enableRandomAdaptation) {
        TrackSelection.Factory factory = tracks.length == 1 ? FIXED_FACTORY : (enableRandomAdaptation ? RANDOM_FACTORY : adaptiveTrackSelectionFactory);
        override = new SelectionOverride(factory, group, tracks);
    }

    // Track array manipulation.

    private static int[] getTracksAdding(SelectionOverride override, int addedTrack) {
        int[] tracks = override.tracks;
        tracks = Arrays.copyOf(tracks, tracks.length + 1);
        tracks[tracks.length - 1] = addedTrack;
        return tracks;
    }

    private static int[] getTracksRemoving(SelectionOverride override, int removedTrack) {
        int[] tracks = new int[override.length - 1];
        int trackCount = 0;
        for (int i = 0; i < tracks.length + 1; i++) {
            int track = override.tracks[i];
            if (track != removedTrack) {
                tracks[trackCount++] = track;
            }
        }
        return tracks;
    }

}
