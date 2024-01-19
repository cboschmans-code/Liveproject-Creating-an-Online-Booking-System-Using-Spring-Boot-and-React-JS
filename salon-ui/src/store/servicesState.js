import {Subject} from "rxjs";
import {useCallback} from "react";

const subject = new Subject();

const initialState = {
    loading: false,
    progress: 0,
    services: [],
    error: ''
};
let state = initialState;

export const serviceStore = {
    init: () => {
        subject.next(state)
    },
    subscribe: (setState) => {
     return subject.subscribe(setState);
    },
    addServices: (newServices) => {

        state = {...state, services: [...state.services, ...newServices]};
        subject.next(state);
    },
    setTimer: () => setInterval(intervalId => {
        state = {...state, progress: state.progress + 50}
        if (state.progress === 100) {
            clearInterval(intervalId);
            intervalId=null;
        }

    }, 1),
    setError: (error) => {
        state = {...state, error: error};
        subject.next(state);
    },
    setLoading: (isLoading) => {
        state = {
            ...state, loading: isLoading}
            subject.next(state);
        },
    unsubscribe : (setState) =>{
        const subscription=   subject.subscribe(setState);
        return ()=>subscription.unsubscribe();
    },
            initialState
    };
