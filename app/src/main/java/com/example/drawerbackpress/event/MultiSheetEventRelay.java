package com.example.drawerbackpress.event;

import com.jakewharton.rxrelay2.PublishRelay;

import io.reactivex.Observable;

public class MultiSheetEventRelay {

    private PublishRelay<MultiSheetEvent> eventRelay = PublishRelay.create();

    public MultiSheetEventRelay(){

    }
    public void sendEvent(MultiSheetEvent event) {
        eventRelay.accept(event);
    }

    public Observable<MultiSheetEvent> getEvents() {
        return eventRelay;
    }

    public static class MultiSheetEvent{

        public @interface Action{
            int GOTO = 0;
            int HIDE = 1;
            int SHOW_IF_HIDDEN = 2;
        }

        public int action;
        public int sheet;

        public MultiSheetEvent(int action, int sheet) {
            this.action = action;
            this.sheet = sheet;
        }

    }
}
