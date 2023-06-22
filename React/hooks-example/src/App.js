import './App.css';
import './exam.css';
import { useEffect, useState, useRef, useContext, createContext, useMemo } from 'react';

// 1. useState 
// 2. useEffect : 어떤 컴포넌트가 마운트(화면에 첫 랜더링 되는거), 업데이트(재랜더링), 언마운트(화면에서 사라질때)
//      특정한 코드를 처리해 주는 함수
// 3. useRef : Ref Object를 리턴하는 hook함수, 리턴된 Ref값은 컴포넌트의 전 생애 기간 동안 유지(새로 랜더링 되도 유지됨)
    // 예 ) const ref = useRef(value) ---> Ref Object (current:value) 리턴되며 ref.current='hello'와 같은 방법으로 값을 바꿀수 있음
    // state의 변화 -> 랜더링 -> 컴포넌트 내부 변수들을 초기화 시킴
    // 반면에 Ref에 변화 -> 노랜더링 -> 값이 보존됨
    //따라서, 랜더링이 되더라도 초기화하면 안되는 값을 저장할때 유용. DOM요소에 접근 할 때 앨리먼트 내에 ref 속성을 만들고 이 ref 속성값을 이용해서 접근이 가능.
// 4. useContext : Redux 또는 Redux Toolkit이 많이 사용됨. 데이터 공유 함수 Context로 공유한 데이터를 받아 오는 역할을 하는 함수
// 5. useMemo : Memoization(반복된 계산을 매번 다시 수행하는 것이 아니라 캐싱 해놓은 결과를 불러내어서 재사용 하는 최적화 기법)기능을 수행
    // usermemo 함수의 2번째 인자인 의존성 배열의 값이 변경 될 떄만 첫번째 인자인 콜백 함수를 실행 시키고, 실행이 안되었을 경우 기존 보관중인 "값"을 리턴
    // userEffect 함수의 2번째 인자에 객체가 있는 경우에 발생하는 문제를 userMemo 함수는 해결할 방법이 있기 때문에
    // userEffect 함수를 대신해서 사용되는 경우가 있음.
// 6. userCallback : userMemo처럼 Memoization 기능을 수행하는 함수로 userMemo가 콜백함수의 리턴 값을 Memoiztion하는 것과 달리 
const App = () => {
  return (
    <div className='exam'> 
      <h1>useState 예제</h1>
      <Exam1></Exam1><br/>
      <Exam2></Exam2>
      <h1>useEffect 예제</h1>
      <Exam3></Exam3><br/>
      <Exam4></Exam4>
      <h1>useRef 예제</h1>
      <Exam5></Exam5>
      <hr></hr>
      <Exam6></Exam6>
      <h1>UserContext 예제</h1>
      <Exam7/>
      <h1>UserMemo 예제</h1>
      <Exam8/>
    </div>
  );
}

const Exam1 = () => {
  const [time, setTime] = useState(1);
  const handleClick = () => {
    console.log(time);
    if(time>12) setTime(0);
    setTime(time+1);
  }
  return (
    <div>
      <span>현재 시간 : {time}시</span>
      <button onClick={handleClick}>시간 변경</button>
    </div>
  );
}

const Exam2 = () => {
  const [names, setNames] = useState(['김철수', '김민수']);
  const [input, setInput] = useState('');
  const hadleInputChange = (e) => {
    setInput(e.target.value);
  }
  const handleClick = () => { // 불변성, 전개연산자(Spread 연산자)
    setNames((prevState) => {return [input,...prevState]})
    setInput(''); 
  }
  return (
    <div>
      <input type='text' value={input} onChange={hadleInputChange} />
      <button onClick={handleClick}>클릭</button>
      {/* map 함수는 자동으로 for 문을 실행 시켜서 인덱스와 값을 인자로 받아옴 */}
      {names.map((name, idx) => (
        <p key={idx}>{name}</p>
      ))}
    </div>
  );
}

// useEfferct
const Exam3 = () => {
  const [count, setCount] = useState(1);
  const [name, setName] = useState('');
  const handleCount = () => {
    setCount(count +1);
  }
  const handleInputChange = (e) => {
    setName(e.target.value);
  }

  // 매번 랜더링이 될 떄 마다 콜백 함수 실행
  useEffect(() => {console.log("랜더링 발생시 마다 콜백함수 실행...")});

  // 의존성 배열(Dependency Array) : 마운트(첫 랜더링이 일어날 때)될 때만 콜백 실행
  useEffect(() => {console.log("마운트시에만 콜백함수 실행...")},[]);

  // 마운트와 count값이 변경 될 떄만 콜백함수가 실행
  useEffect(() => {console.log("미운트와 count 값이 바뀔때 만 실행...")},[count]);

  return (
    <div>
      <button onClick={handleCount}>클릭</button>
      <span>count : {count}</span>
      <input type="text" value={name} placeholder='이름을 입력 하세요' onChange={handleInputChange}/><br/>
      <span>name : {name}</span>
    </div>
  );
}

// clean
const Exam4 = () => {
  const [showTimer, setShowTimer] = useState(false);
  let btn_name ;
  if(showTimer) btn_name = "타이머 중지";
    else btn_name = "타이머 실행";
  return (
    <div>
      {/* showtimer가 true 일때 <Timer/>를 실행 */}
      {showTimer && <Timer />}
      <button onClick={()=>{setShowTimer(!showTimer)}}>{btn_name}</button>
    </div>
  );
}

const Timer = () => {
  useEffect(() => {
    const timer = setInterval(()=>{
      console.log("타이머 돌아가는 중");
    },1000);
    return () => {
      clearInterval(timer);
      console.log("타이머가 종료 되었습니다.")
    }
  }, [])
  return (
    <div>
      <span>타이머를 시작 합니다. 콘솔을 보세요.</span>
    </div>
  );
}

// useRef 예제
const Exam5 = () => {
  const [count, setCount] = useState(1);
  const countRef = useRef(1);  // countRef가 {current:1} 객체를 참조 (객체의 주소값 참조) 
  let countVar = 0;
  const renderCount = useRef(1);

  const increaseCountState = () => {
    setCount(count+1);
  }
  const increaseCountVar = () => {
    countVar = countVar +1;
    console.log("Var = " +countVar);
  }
  const increaseCountRef = () => {
    countRef.current = countRef.current + 1;
    console.log("Ref = " +countRef.current);
  }

  useEffect(() => { // React는 처음 랜더링 할때 검사를 위해 두번 랜더링함
    renderCount.current = renderCount.current+1;
    console.log("Exam5 예제 " + renderCount.current + "번째 랜더링");
    
  })

  return (
    <div>
      <p>State : {count}</p>    
      <p>Ref : {countRef.current}</p>    
      <p>Var : {countVar}</p>    
      <button onClick={increaseCountState}>State 증가</button>
      <button onClick={increaseCountRef}>Ref 증가</button>
      <button onClick={increaseCountVar}>Var 증가</button>
    </div>
  );
}

const Exam6 = () => {
  const inputRef = useRef();

  useEffect(() => {
    inputRef.current.focus();
  });

  const login = () => {
    alert(`안녕하세요? ${inputRef.current.value} 님`); // 탬플리트 표현법
    inputRef.current.focus();
  }
  return (
    <div>
      <input type='text' ref={inputRef} placeholder='이름을 입력 하세요' />
      <button onClick={login}>로그인</button> 
    </div>
  );
}

const Exam7 = () => {
  const [isDark, setIsDark] = useState(false);
  return (
    <div>
      <UserContext.Provider value={"최대훈"}> {/* Provider로 저장소의 영향이 미치는 컴포넌트를 둘러쌈 */}
        <TheemContext.Provider value={{isDark, setIsDark}}>
          <Page />

        </TheemContext.Provider>
      </UserContext.Provider>
    </div>
  );
}

const TheemContext = createContext(null); //저장소 생성
const UserContext = createContext(null); //저장소 생성

const Page = () => {
  return (
    <div>
      <Header />
      <Content />
      <Footer />
    </div>
  );
}

const Header = () => {
  const {isDark} = useContext(TheemContext); // 저장소에 보관된 값을 가져옴
  const user = useContext(UserContext); // 저장소에 보관된 값을 가져옴
  return (
    <div>
      <header className='header'
        style={{
          backgroundColor: isDark ? 'black' : 'lightgray',
          color:isDark ? 'white':'black',
        }}>
          <h1>어서오세요 {user} !!!</h1>
        </header>

    </div>
  );
}

const Content = () => {
  const {isDark} = useContext(TheemContext);
  const user = useContext(UserContext);

  return (
    <div className='content'
    style={{
      backgroundColor:isDark ? 'black' : 'white',
      color: isDark ? 'white' : 'black'
    }}> 
    <p>{user}님, 좋은 하루 되세요.</p>

    </div>
  );
}

const Footer =() => {
  const {isDark, setIsDark} = useContext(TheemContext);
  return (
    <div>
      <footer className='footer'
        style={{
          backgroundColor:isDark?'black':'lightgray',
        }}>
          <button className='button' onClick={() => setIsDark(!isDark)}>다크모드</button>
      </footer>
    </div>
  );
}

const Exam8 = () => {
  const [hardNumber, setHardNumber] = useState();
  const [easyNumber, setEasyNumber] = useState();

  // const hardSum = hardCalculate(hardNumber);
  const hardSum = useMemo(() => {
    return hardCalculate(hardNumber);
  }, [hardNumber])
  const easySum = easyCalulate(easyNumber);
  return (
    <div>
      <h3>짜증나는 계산기</h3>
      <input type='number' value={hardNumber} onChange={(e) => {setHardNumber(parseInt(e.target.value))}} />
      <span> +10,000 = {hardSum}</span>
      <hr></hr>
      <h3>조금 덜 짜증나는 계산기</h3>
      <input type='number' value={easyNumber} onChange={(e) => {setEasyNumber(parseInt(e.target.value))}}/>
      <span> +10,000 = {easySum}</span>
      
    </div>
  );
}

const hardCalculate = (n) => {
  console.log("짜증나는 계산기 랜더링");
  for(let i=0; i<999999999; i++){
    return n + 100000
  }
}

const easyCalulate = (n) => {
  console.log("조금 덜 짜증나는 계산기 랜더링");
  return n + 100000
}



export default App;
