
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 daimajia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package vn.loitp.core.utilities.animation;

import vn.loitp.core.utilities.animation.attention.BounceAnimator;
import vn.loitp.core.utilities.animation.attention.FlashAnimator;
import vn.loitp.core.utilities.animation.attention.PulseAnimator;
import vn.loitp.core.utilities.animation.attention.RubberBandAnimator;
import vn.loitp.core.utilities.animation.attention.ShakeAnimator;
import vn.loitp.core.utilities.animation.attention.StandUpAnimator;
import vn.loitp.core.utilities.animation.attention.SwingAnimator;
import vn.loitp.core.utilities.animation.attention.TadaAnimator;
import vn.loitp.core.utilities.animation.attention.WaveAnimator;
import vn.loitp.core.utilities.animation.attention.WobbleAnimator;
import vn.loitp.core.utilities.animation.bouncing_entrances.BounceInAnimator;
import vn.loitp.core.utilities.animation.bouncing_entrances.BounceInDownAnimator;
import vn.loitp.core.utilities.animation.bouncing_entrances.BounceInLeftAnimator;
import vn.loitp.core.utilities.animation.bouncing_entrances.BounceInRightAnimator;
import vn.loitp.core.utilities.animation.bouncing_entrances.BounceInUpAnimator;
import vn.loitp.core.utilities.animation.fading_entrances.FadeInAnimator;
import vn.loitp.core.utilities.animation.fading_entrances.FadeInDownAnimator;
import vn.loitp.core.utilities.animation.fading_entrances.FadeInLeftAnimator;
import vn.loitp.core.utilities.animation.fading_entrances.FadeInRightAnimator;
import vn.loitp.core.utilities.animation.fading_entrances.FadeInUpAnimator;
import vn.loitp.core.utilities.animation.fading_exits.FadeOutAnimator;
import vn.loitp.core.utilities.animation.fading_exits.FadeOutDownAnimator;
import vn.loitp.core.utilities.animation.fading_exits.FadeOutLeftAnimator;
import vn.loitp.core.utilities.animation.fading_exits.FadeOutRightAnimator;
import vn.loitp.core.utilities.animation.fading_exits.FadeOutUpAnimator;
import vn.loitp.core.utilities.animation.flippers.FlipInXAnimator;
import vn.loitp.core.utilities.animation.flippers.FlipInYAnimator;
import vn.loitp.core.utilities.animation.flippers.FlipOutXAnimator;
import vn.loitp.core.utilities.animation.flippers.FlipOutYAnimator;
import vn.loitp.core.utilities.animation.rotating_entrances.RotateInAnimator;
import vn.loitp.core.utilities.animation.rotating_entrances.RotateInDownLeftAnimator;
import vn.loitp.core.utilities.animation.rotating_entrances.RotateInDownRightAnimator;
import vn.loitp.core.utilities.animation.rotating_entrances.RotateInUpLeftAnimator;
import vn.loitp.core.utilities.animation.rotating_entrances.RotateInUpRightAnimator;
import vn.loitp.core.utilities.animation.rotating_exits.RotateOutAnimator;
import vn.loitp.core.utilities.animation.rotating_exits.RotateOutDownLeftAnimator;
import vn.loitp.core.utilities.animation.rotating_exits.RotateOutDownRightAnimator;
import vn.loitp.core.utilities.animation.rotating_exits.RotateOutUpLeftAnimator;
import vn.loitp.core.utilities.animation.rotating_exits.RotateOutUpRightAnimator;
import vn.loitp.core.utilities.animation.sliders.SlideInDownAnimator;
import vn.loitp.core.utilities.animation.sliders.SlideInLeftAnimator;
import vn.loitp.core.utilities.animation.sliders.SlideInRightAnimator;
import vn.loitp.core.utilities.animation.sliders.SlideInUpAnimator;
import vn.loitp.core.utilities.animation.sliders.SlideOutDownAnimator;
import vn.loitp.core.utilities.animation.sliders.SlideOutLeftAnimator;
import vn.loitp.core.utilities.animation.sliders.SlideOutRightAnimator;
import vn.loitp.core.utilities.animation.sliders.SlideOutUpAnimator;
import vn.loitp.core.utilities.animation.specials.RollInAnimator;
import vn.loitp.core.utilities.animation.specials.RollOutAnimator;
import vn.loitp.core.utilities.animation.zooming_entrances.ZoomInAnimator;
import vn.loitp.core.utilities.animation.zooming_entrances.ZoomInDownAnimator;
import vn.loitp.core.utilities.animation.zooming_entrances.ZoomInLeftAnimator;
import vn.loitp.core.utilities.animation.zooming_entrances.ZoomInRightAnimator;
import vn.loitp.core.utilities.animation.zooming_entrances.ZoomInUpAnimator;
import vn.loitp.core.utilities.animation.zooming_exits.ZoomOutAnimator;
import vn.loitp.core.utilities.animation.zooming_exits.ZoomOutDownAnimator;
import vn.loitp.core.utilities.animation.zooming_exits.ZoomOutLeftAnimator;
import vn.loitp.core.utilities.animation.zooming_exits.ZoomOutRightAnimator;
import vn.loitp.core.utilities.animation.zooming_exits.ZoomOutUpAnimator;

public enum Techniques {
    Flash(FlashAnimator.class),
    Pulse(PulseAnimator.class),
    RubberBand(RubberBandAnimator.class),
    Shake(ShakeAnimator.class),
    Swing(SwingAnimator.class),
    Wobble(WobbleAnimator.class),
    Bounce(BounceAnimator.class),
    Tada(TadaAnimator.class),
    StandUp(StandUpAnimator.class),
    Wave(WaveAnimator.class),

    RollIn(RollInAnimator.class),
    RollOut(RollOutAnimator.class),

    BounceIn(BounceInAnimator.class),
    BounceInDown(BounceInDownAnimator.class),
    BounceInLeft(BounceInLeftAnimator.class),
    BounceInRight(BounceInRightAnimator.class),
    BounceInUp(BounceInUpAnimator.class),

    FadeIn(FadeInAnimator.class),
    FadeInUp(FadeInUpAnimator.class),
    FadeInDown(FadeInDownAnimator.class),
    FadeInLeft(FadeInLeftAnimator.class),
    FadeInRight(FadeInRightAnimator.class),

    FadeOut(FadeOutAnimator.class),
    FadeOutDown(FadeOutDownAnimator.class),
    FadeOutLeft(FadeOutLeftAnimator.class),
    FadeOutRight(FadeOutRightAnimator.class),
    FadeOutUp(FadeOutUpAnimator.class),

    FlipInX(FlipInXAnimator.class),
    FlipOutX(FlipOutXAnimator.class),
    FlipInY(FlipInYAnimator.class),
    FlipOutY(FlipOutYAnimator.class),
    RotateIn(RotateInAnimator.class),
    RotateInDownLeft(RotateInDownLeftAnimator.class),
    RotateInDownRight(RotateInDownRightAnimator.class),
    RotateInUpLeft(RotateInUpLeftAnimator.class),
    RotateInUpRight(RotateInUpRightAnimator.class),

    RotateOut(RotateOutAnimator.class),
    RotateOutDownLeft(RotateOutDownLeftAnimator.class),
    RotateOutDownRight(RotateOutDownRightAnimator.class),
    RotateOutUpLeft(RotateOutUpLeftAnimator.class),
    RotateOutUpRight(RotateOutUpRightAnimator.class),

    SlideInLeft(SlideInLeftAnimator.class),
    SlideInRight(SlideInRightAnimator.class),
    SlideInUp(SlideInUpAnimator.class),
    SlideInDown(SlideInDownAnimator.class),

    SlideOutLeft(SlideOutLeftAnimator.class),
    SlideOutRight(SlideOutRightAnimator.class),
    SlideOutUp(SlideOutUpAnimator.class),
    SlideOutDown(SlideOutDownAnimator.class),

    ZoomIn(ZoomInAnimator.class),
    ZoomInDown(ZoomInDownAnimator.class),
    ZoomInLeft(ZoomInLeftAnimator.class),
    ZoomInRight(ZoomInRightAnimator.class),
    ZoomInUp(ZoomInUpAnimator.class),

    ZoomOut(ZoomOutAnimator.class),
    ZoomOutDown(ZoomOutDownAnimator.class),
    ZoomOutLeft(ZoomOutLeftAnimator.class),
    ZoomOutRight(ZoomOutRightAnimator.class),
    ZoomOutUp(ZoomOutUpAnimator.class);

    private Class animatorClazz;

    private Techniques(Class clazz) {
        animatorClazz = clazz;
    }

    public BaseViewAnimator getAnimator() {
        try {
            return (BaseViewAnimator) animatorClazz.newInstance();
        } catch (Exception e) {
            throw new Error("Can not init animatorClazz instance");
        }
    }
}
