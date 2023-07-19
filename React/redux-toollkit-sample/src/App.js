import {legacy_createStore as createStore} from 'redux';
import { Provider, uesSelector, useDispatch, useSelector } from 'react-redux';
import {createSlice, configureStore, createASyncThunk} from '@reduxjs/toolkit';

// redux 라이브러리의 reducer 함수
const reducer = (state, action) => {
  if(action.type ==='up') {
    return {...state, value:state.value + action.step} // 불변성 유지를 위하여 스레드 연산자를 이용하여 복사
  }
  return state;
}

// redux-toolkit을 사용
const counterSlice = createSlice({
  name: 'counterSlice',  // slice 이름
  initialState:{value:0},
  reducers { //type 별로 실행 함수를 만듬
    up:(state, action) => {
      console.log(action);
      state.value = state.value + action.step // redux-toolkit을 사용하면 불변성 고려할 필요가 없음
    }
  }
})

const storeReduxToolkit = configureStore({
  reducer: {
    xxx: counterSlice.reducer
  }
})

const initialStateRedux = {value:0}
// Redux 에서는 하나의 store에 모든 state가 저장되는 것에 반해서 redux-toolkit은 각각의 state 별로 slice를 사용해서
//   state를 모아 둘수가 있고, 이 slice를 configureStore로 하나의 store로 모아서 저장
const storeRedux = createStore(reducer, initialStateRedux);

const App = () => {
  
  return (
    <div>
      <Provider store={storeRedux}>
      < CounterRedux />
      </Provider>
      <Provider store={}>
        <CounterReduxToolKit />
      </Provider>
    </div>
  );
}

// Redux 라이브러리 사용
const CounterRedux = () => {
  const dispatch = useDispatch();
  const countRedux = useSelector((State) => state.value);
  return (
    <div>
      <button onClick={() => {
        dispatch({type:'up', step:2}) // dispatch의 인자. 즉, action을 reducer에게로 전송
      }}>redux 사용해서 state 값 증가</button>
      <br/>

    </div>
  );
}

// redux-toolkit 이용
const CounterReduxToolKit = () => {
  const dispatch = useDispatch();

  return (
    <div>
      <button onClick={() => {
        type:
      }}>redux toolkit을 사용해서 state 값 증가</button>
    </div>
  );
}

export default App;