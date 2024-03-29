import './App.css';
import {useState} from 'react';
import {legacy_createStore as createStore} from 'redux';
import {Provider, useSelector,useDispatch } from 'react-redux';

// 0. Provider -> redux가 영향을 미치는 컴포넌트를 둘러 쌈
// 1. useReducer -> state, action을 인자로 받고, 받은 state가 action에 의해 가공이 되고, 가공된 state를 반환
// 2. store -> reducer에 의해 만들어진 state를 저장하는 중앙 저장소. createStore 함수에 reducer를 인자로 받아서 만들어짐
// 3. useSelector -> store에서 state를 가지고 옴
// 4. useDispatch -> reduce에게 action을 요청

const reducer = (currentState, action) => {
  if(currentState === undefined) {
    return {number: 1};
  }

  const newState = {...currentState}; // 의존성을 유지하기 위해서 스레드 연산자로 얕은 복사
  if(action.type === 'PLUS') {
    newState.number++;
  }

  return newState;
}

const store = createStore(reducer); // store 생성

const App = () => {
    const [number, setNumber] = useState(1);
    
    return (
      <div id="container">
        <h1>Root : {number}</h1>
        <div id="grid">
          {/* store라는 props에 createStore에 의해 만들어진 store를 넣어준다 */}
          <Provider store={store}> 
            <Left1 number={number}></Left1>
            <Right1 onIncrease={() => {setNumber(number+1)}}></Right1>
          </Provider>
        </div>

      </div>
    );
}

const Left1 = (props) => {
  return (
    <div>
      <h1>Left1 : {props.number}</h1>
      <Left2 number={props.number}></Left2>
    </div>
  );
}

const Left2 = (props) => {

  return (
    <div>
      <h1>Left2 : {props.number}</h1>
      <Left3 number={props.number}></Left3>

    </div>
  );
}

const Left3 = (props) => {
  const dispatch = useDispatch();
  const number = useSelector((state) => state.number)
  return (
    <div>
      <h1>Left3 : {props.number} redux : {number}</h1>
      <button onClick={() => dispatch({type: 'PLUS'})}>redux로 더하기</button>
    </div>
  );
}

const Right1 = (props) => {
  return (
    <div>
      <h1>Right1 : </h1>
      <Right2 onIncrease = {() => props.onIncrease()}></Right2>
    </div>
  );
}

const Right2 = (props) => {
  return (
    <div>
      <h1>Right2 : </h1>
      <Right3  onIncrease = {() => props.onIncrease()}></Right3>
    </div>
  );
}

const Right3 = (props) => {
  return (
    <div>
      <h1>Right3 : </h1>
      <input type='button' value="더하기" onClick={()=> {props.onIncrease(); console.log(props.number);}}/>
    </div>
  );
}
export default App;
