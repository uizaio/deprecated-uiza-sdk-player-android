package com.google.android.exoplayer2.ui.language;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.google.android.exoplayer2.ui.R;

/**
 * Created by www.muathu@gmail.com on 5/13/2017.
 */

public class LanguageView extends RelativeLayout {
    private final String TAG = getClass().getSimpleName();
    private RowView rowSubtitleOn;

    public LanguageView(Context context) {
        super(context);
        init();
    }

    public LanguageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LanguageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.language_view, this);

        this.rowSubtitleOn = (RowView) findViewById(R.id.row_subtitle_on);

        rowSubtitleOn.setTvDescription("Subtitle ON");
    }

}