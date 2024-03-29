import TodoTemplate from './components/TodoTemplate';
import TodoInsert from './components/TodoInsert';
import TodoList from './components/TodoList';
import { useState } from 'react';

function App() {
  const [todos, setTodos] = useState([
    {
      id: 1,
      text : '리엑트의 기초 알아보기',
      checked : true,
    },
    {
      id: 1,
      text : '컴포넌트 스타일링 해보기',
      checked : true,
    },
    {
      id: 1,
      text : '일정관리 앱 만들어 보기',
      checked : false,
    },
  ]);

  return (
   
    <TodoTemplate>
      <TodoInsert />
      <TodoList />
    </TodoTemplate>
  );
}

export default App;
