package com.example.drawerbackpress.event;

import com.example.drawerbackpress.sheet.MultiSheetView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.jakewharton.rxrelay2.BehaviorRelay;

import io.reactivex.Observable;

public class MultiSheetSlideEventRelay {

    private final BehaviorRelay<SlideEvent> eventRelay = BehaviorRelay.create();


    public MultiSheetSlideEventRelay() {
    }

    public void sendEvent(SlideEvent event) {
        eventRelay.accept(event);
    }

    public Observable<SlideEvent> getEvents() {
        return eventRelay;
    }

    public static class SlideEvent {

        public final int sheet;
        @BottomSheetBehavior.State
        public final int state;
        public final float slideOffset;

        public SlideEvent(int sheet, int state, float slideOffset) {
            this.sheet = sheet;
            this.state = state;
            this.slideOffset = slideOffset;
        }

        public SlideEvent(int sheet, int state) {
            this(sheet, state, -1f);
        }

        public SlideEvent(int sheet, float slideOffset) {
            this(sheet, -1, slideOffset);
        }

        public boolean nowPlayingExpanded() {
            return sheet == MultiSheetView.Sheet.FIRST && state == BottomSheetBehavior.STATE_EXPANDED;
        }

        public boolean nowPlayingCollapsed() {
            return sheet == MultiSheetView.Sheet.FIRST && state == BottomSheetBehavior.STATE_COLLAPSED;
        }
    }
}
