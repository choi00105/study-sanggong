import {Route, Routes} from 'react-router-dom';
import Login from './component/Login.js';
import Signup from './component/Signup.js';
import ListBoard from './component/ListBoard.js';
import ViewBoard from './component/ViewBoard.js';
import WriteBoard from './component/WriteBoard.js';
import ModifyBoard from './component/ModifyBoard.js';
import DeleteBoard from './component/DeleteBoard.js';

const App = () => {
  return(
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/member/signup" element={<Signup />} />
      <Route path="/board/list" element={<ListBoard />} />
      <Route path="/board/view" element={<ViewBoard />} />
      <Route path="/board/write" element={<WriteBoard />} />
      <Route path="/board/modify" element={<ModifyBoard />} />
      <Route path="/board/delete" element={<DeleteBoard />} />
    </Routes>
  );
}
export default App;
