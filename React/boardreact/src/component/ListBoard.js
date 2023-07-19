import Logo from '../images/logo.jpg';
import getCookie from './GetCookie';
import {useEffect, useState, useCallback} from 'react';
import {useSearchParams, Link, NavLink} from 'react-router-dom';
import dayjs from 'dayjs';

const ListBoard = () => {

    const [params, setParams] = useSearchParams();
    const [list, setList] = useState([]);
    const [pageList, setPageList] = useState('');
    const [page, setpage] = useState(params.get('page'));
    const [keyword, setKeyword] = useState(params.get('keyword') === null ? '': params.get('keyword'))
    const emailCookie = getCookie('email');
    const passwordCookie = getCookie('password');
    const roleCookie = getCookie('role');

    const styled = {
        textDecorationLine: 'none',
        cursor: 'hand'
    }
 
    dayjs.locale('ko');

    const getList = async () => {
        await fetch(`http://localhost:8080/restapi/list?page=${page}&keyword=${keyword}`,{method : 'GET'})
            .then((response) => response.json())
            .then((data) => setList(data.content))
            .catch((error)=> { console.log("error = " + error);} );                
    }
    const getPageList = async () => {
        await fetch(`http://localhost:8080/restapi/pagelist?page=${page}&keyword=${keyword}`,{method : 'GET'})
            .then((response) => response.json())
            .then((data) => setPageList(data.pagelist))
            .catch((error)=> { console.log("error = " + error);} );                
    }
    useEffect(()=> {
        getList();
        getPageList();    
    },[]);

    const Search = useCallback(() => {
        getList();
        getPageList();
    },[keyword]);

    const logout = () => {
        document.cookie = 'email=' + emailCookie + ';path=/; max-age=0';
        document.cookie = 'password=' + passwordCookie + ';path=/; max-age=0';
        document.cookie = 'role=' + roleCookie + ';path=/; max-age=0';
        document.location.href="/";

    }

    const onKeyDown = (e) => {
        if(e.key === 'Enter'){
            Search();
        }    
    };


    return(
        <div>
            <div>
		        <img id="topBanner" src={Logo} alt="서울기술교육센터" />	
	        </div>
            <div className='tableDiv'>
                <h1>게시물 목록 보기</h1>
		        <input style={{width:'40%',height:'30px',border:'2px solid #168',fontSize: '16px'}}  
			        type="text" value={keyword} onChange={(e)=>setKeyword(e.target.value)} placeholder="검색할 제목,작성자이름 및 내용을 입력해 주세요" />
		        <input style={{width:'5%',height:'30px',background:'#158',color:'white',fontWeight:'bold',
                    cursor:'pointer'}} type="button" value="검색" onClick={Search} />
		        <br/><br/>
                <table className="InfoTable">
                    <thead>
                        <tr>
                            <th>번호</th>		
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                    {list.map((list) => (
                        
                        <tr key = {list.seqno}>
                            <td>{list.seqno}</td>
                            <td className='column'><NavLink to={`/board/view?seqno=${list.seqno}&page=${page}&keyword=${keyword}`} style={styled}>{list.title}</NavLink></td>
                            <td>{list.writer}</td>
                            <td>{list.hitno}</td>
                            <td>{dayjs(list.regdate).format('YYYY-MM-DD HH:mm:ss')}</td>
                        </tr>
                    ))
                    }   
                    </tbody>
                </table>
                <br />
                <div dangerouslySetInnerHTML={{ __html: pageList }} ></div>
                <br/>
                <div className="bottom_menu">
                    <a href="/board/list?page=1">처음으로</a>&nbsp;&nbsp;
                    <Link to="/board/write">글쓰기</Link>&nbsp;&nbsp;
                    <Link to="/board/chat">채팅 하기</Link>&nbsp;&nbsp;
                    <Link to="/member/memberInfo">사용자관리</Link>&nbsp;&nbsp;
                    <Link to="/master/sysmanage">시스템관리</Link>&nbsp;&nbsp;
                    <Link onClick={logout}>로그아웃</Link>		
                </div>
		        <br/><br/>
            </div>

        </div>
    )

}

export default ListBoard;