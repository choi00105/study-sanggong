import './App.css';
import SubApp from './SubApp';
import ProtoType from 'prop-types'
import { useState } from 'react';


function App() {
  const name = "리엑트";
  return (
    <div className="react">
      <h1>Props 예제</h1>
      <h2>이번 프로젝트는 {name} 프로젝트 입니다</h2>
      <hr/>

      <SubApp></SubApp>
      <h1>state 에제</h1>
      <MyComponent name="최대훈" favoriteNumber={3} >KIM</MyComponent>
      <hr/>
      <MyState></MyState>
      <br/>
      <Say></Say>
      <EventPractice></EventPractice>
      <IterationSample/>
    </div>
  );
}

const MyComponent = ({name, favoriteNumber, children}) => {

  return (
    <div>
      안녕하세요 제 이름은 {name} 입니다. <br></br>
      Children 값은 {children} 입니다. <br></br>
      제가 가장 좋아하는 숫자는 {favoriteNumber} 입니다.
    </div>
  );
}

// 전달하는 props값이 없을 경우 사용
MyComponent.defaultProps = {
  name: '아무개'
}

MyComponent.prototype = {
  name: ProtoType.string,
  favoriteNumber: ProtoType.number
}

const MyState = () => {
  const [number, setNumber] =  useState(1);
  let numberVar = 1;
  
  const handleClickVar =() => {
    console.log("변수 = " + ++numberVar);
  }

  return (
    <div>
      <p>변수 :  {numberVar}</p>
      <p>State :  {number}</p>
      <button onClick={handleClickVar}>변수</button>
      <button onClick={() => {
        setNumber(number+1);
        console.log(number);
      }}>state</button>
    </div>
  );
  
}

const Say = () => {
  const [message, setMessage] = useState('');

  const [color, setColor] = useState('black')

  const onClickEnter = () => {
    setMessage('안녕하세요~');

  }

  const onClickLeave = () => {
    setMessage('안녕히 가세요~');
  }

  return (
    <div>
      <button onClick={onClickEnter}>입장</button>
      <button onClick={onClickLeave}>퇴장</button>
      <h1 style={{color}}>{message}</h1>
      <button style={{color:'red'}} onClick={() => setColor('red')}>빨간색</button>
      <button style={{color:'green'}} onClick={() => setColor('green')}>초록색</button>
      <button style={{color:'blue'}} onClick={() => setColor('blue')}>파란색</button>
    </div>
  );
}

const EventPractice = () => {
  const [username, setUsername] = useState('');
  const [message, setMessage] = useState('');

  const onChangeUsername = (e) => {
    setUsername(e.target.value);
  }

  const onChangeMessage = (e) => {
    setMessage(e.target.value);
  }
  const onClick = () => {
    alert(username + ' : ' + message);
    setUsername("");
    setMessage("");

  }
  return (
    <div>
      <h2>이벤트 연습</h2>
      <input type='text' name='username' value={username} placeholder='사용자명'
        onChange={onChangeUsername}/>
      <input type='text' name='message' value={message} placeholder='입력...'
        onChange={onChangeMessage}/>
      <button onClick={onClick}>확인</button>

    </div>
  );
}

const IterationSample = () => {
  const [names, setNames] = useState([
    {id:1, text:'눈사람'},
    {id:2, text:'얼음'},
    {id:3, text:'눈'},
    {id:4, text:'바람'},
  ])

  const [inputText, setInputText] = useState('');
  const [nextId, setnextId] = useState('');
  // map 함수 : 자동으로 for문을 돌려서 k 와 v 를 반환
  // react 에서는 반복을 통해서 엘레멘트를 출력할 경우 엘레멘트 속성에 반드시 키 값이 있어야 함
  const onRemove = (id) => {
    const nextNames = names.filter((name) => name.id !== id);
    setNames(nextNames);
  }
  const nameList = names.map((name) => (
    <li key={name.id} onDoubleClick={() => onRemove(name.id)}>{name.text}</li>
  ))

  const onChange = (e) => {
    setInputText(e.target.value);
  }
  const onClick = (e) => {
    const nextNames = names.concat({
      id: nextId,
      text: inputText
    });
    setnextId(nextId + 1);
    setNames(nextNames);
    setInputText('');
  }
  return (
    <div>
      <h2>엘레멘트 반복 처리 예제</h2>
      {/* inputType State가 변경이 되면 랜더링이 일어남 */}
      <input type='text' value={inputText} onChange={onChange} />
      <button onClick={onClick}> 추가</button>
      <ul>{nameList}</ul>
    </div>
  );
}
export default App;
