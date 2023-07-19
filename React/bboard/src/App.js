import './App.css';
import {Route, Routes} from 'react-router-dom';
import Login from './component/login';
import Signup from './component/Signup';
import ListBoard from './component/ListBoard';
import ViewBoard from './component/ViewBoard';
import WriteBoard from './component/WriteBoard';
import ModifyBoard from './component/ModifyBoard';
import DeleteBoard from './component/DeleteBoard';

const App = () => {
  return (
    <Routes>
      <Route path='/' element={<Login />} />
      <Route path='/member/signup' element={<Signup />} />
      <Route path='/board/list' element={<ListBoard />} />
      <Route path='/board/view' element={<ViewBoard />} />
      <Route path='/board/write' element={<WriteBoard />} />
      <Route path='/board/Modify' element={<ModifyBoard />} />
      <Route path='/board/delete' element={<DeleteBoard />} />


    </Routes>
  );
}

export default App;
