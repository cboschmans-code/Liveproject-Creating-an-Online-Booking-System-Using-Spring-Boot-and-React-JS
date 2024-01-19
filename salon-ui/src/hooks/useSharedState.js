import {serviceStore} from "../store/servicesState";
import {useCallback, useEffect, useMemo, useState} from "react";

export const useSharedState = (id) => {

    // Use the useState hook to create a local state
    const [state, setState] = useState(serviceStore.initialState);

    // Use the useEffect hook to subscribe to the observable and update the state



    // Return the state and the update function
    return [state];
}