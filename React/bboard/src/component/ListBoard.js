import Logo from '../images/뉴진스.jpg'
import '../App.css';
import '../css/ListBoard.css';
import { useState,useEffect } from 'react';
import { useSearchParam, Link, NavLink  } from 'react-router-dom';

import getCookie from './GetCookie';
import { keyboard } from '@testing-library/user-event/dist/keyboard';

const ListBoard = () => {

    const [params, setParams] = useSearchParam();
    const [list, setList] = useState([]);
    const [pageList, setPageList] = useState('');
    const [page,setPage] = useState(params.get('page'));
    // const [keyword, setKeyword] = useState(params.get('keyword') === null ? '': params.get('keyword'));
    // const emailCookie = getCookie('email');
    // const passwordCookie = getCookie('password');
    // const roleCookie = getCookie('role');

    const getList = async () => {
        await fetch(`http://localhost:8080/restapi/list?page=${page}&keyboard=${keyboard}`,{method: 'GET'})
            .then((response)=>response.json())
            .then((data)=> setList(data.content))
    }

    useEffect(() => {},[])

    const press = () => {

    }

    const search = () => {
        
    }

    return (
        <div>
            <div>
		<img id="topBanner" src={Logo} alt="서울기술교육센터" />	
	</div>
	
	<div className="tableDiv">
		<h1>게시물 목록 보기</h1>
		<input style={{width: '40%' ,height: '30px', border: '2px solid #168', fontSize: '16px'}}
			type="text" name="keyword" placeholder="검색할 제목,작성자이름 및 내용을 입력해 주세요" onKeyDown={press()} />
		<input style={{width: '5%', height: '30px', background: '#158', color: 'white', fontWeight: 'bold', cursor: 'pointer'}}
			type="button" value="검색" onClick={search()} />
		<br/><br/>
		<table className="InfoTable">
			<tr>
				<th>번호</th>		
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			
			<tbody>
				<tr>
					<td></td>
					<td style={{textAlign:'left'}}></td>
					<td></td>
					<td></td>				
					<td></td>
				</tr>
			</tbody>	
		</table>
		<br/>
		<div></div>
		<br/>
		<div className="bottom_menu">
			<a href="/board/list?page=1">처음으로</a>&nbsp;&nbsp;
			<a href="/board/write">글쓰기</a>&nbsp;&nbsp;
			<a href="/board/chat">채팅 하기</a>&nbsp;&nbsp;
			<a href="/member/memberInfo">사용자관리</a>&nbsp;&nbsp;
			<a href="/master/sysManage">시스템관리</a>&nbsp;&nbsp;
			<a href="javascript:logout()">로그아웃</a>		
		</div>
		<br/><br/>
		
	</div>
        </div>
    )

}

export default ListBoard;