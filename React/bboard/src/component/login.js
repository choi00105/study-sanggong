import '../App.css';
import '../css/Login.css';
import Logo from '../images/뉴진스.jpg';
import { useRef, useState , useEffect} from 'react';
import CryptoJS from 'crypto-js';
import { Navigate, Link} from 'react-router-dom';
import getCookie from './GetCookie';


const Login = () => {
    // 쿠키 변수 초기화
    let emailCookie = getCookie('email');
    let passwordCookie = getCookie('password');
    let authkeyCookie = getCookie('authkey');

    // state 초기화
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');

    // Ref 초기화
    const emailRef = useRef();
    const passwordRef = useRef();
    const rememberEmailRef = useRef();
    const rememberPasswordRef = useRef();
    const rememberMeRef = useRef();
    
	//이메일 체크 관리
	const checkRememberEmail = (e) => {
		if(e.target.value)
            rememberMeRef.current.checked = false;
	}
	
	//패스워드 체크 관리
	const checkRememberPassword = (e) => {
		if(e.target.value)
            rememberMeRef.current.checked = false;
	}	

    //자동로그인 체크 관리
	const checkRememberMe = (e) => {
		
		if(e.target.value){
			rememberEmailRef.current.checked = false;
			rememberPasswordRef.current.checked = false;
		}
	}

    // 첫번쨰 랜더링 시에 쿠키를 읽어서 email, pw, 자동로그인 쿠키 존재 여부를 확인 -> 저장된 email, pw, authkey 겂을 가져와서
    // email, pw는 input value에 넣고 authkey는 서버로 보내서 검증을 거친다
    useEffect(() => checkBoxConfirm(),[])
    const checkBoxConfirm = async() => {
        // email 쿠키 존재 여부 확인 후 email 쿠키가 존재하면 email state에 할당
        if(emailCookie !== undefined) { // email 쿠키가 존재하면
            setEmail(emailCookie); // email state에 쿠키 값을 할당
            rememberEmailRef.current.checked = true; // email 기억하기 체크
        } else rememberEmailRef.current.checked = false;

        // 패스워드 존재 여부 확인 후 password 쿠키가 존재하면 password state에 할당
        if(passwordCookie !== undefined) { // password 쿠키가 존재하면 
            // Base64로 인코딩 된 password를 디코딩(복호화)
            const decrypt = CryptoJS.enc.Base64.parse(passwordCookie);
		    const hashData = decrypt.toString(CryptoJS.enc.Utf8);		    
		    setPassword(hashData);
            rememberPasswordRef.current.checked = true;
        } else rememberPasswordRef.current.checked = false;

        // 자동로그인 쿠키 존재 야브 확인 후 지동 로그인 쿠키가 존재하면
        // autologin=PASS 쿼리를 포함 하여 서버로 authkey 값을 비동기 전송
        if(authkeyCookie !== undefined) {
            let formData = new FormData();
			formData.append("authkey",authkeyCookie);
			await fetch('/restapi/loginCheck?autologin=PASS',{
				method : 'POST',
				body : formData
			}).then((response) => response.json())
			  .then((data) => {
				 if(data.message === 'good'){			
                    <Navigate to={"/board/list?page=1}/"} />
				} else {
					alert("시스템 장애로 자동 로그인이 실패 했습니다.");		 
				}		  
		    }).catch((error)=> { console.log("error = " + error);} );
        }
    } 

    //REST API 서버와 비동기 통신으로 아이디/패스워드 값을 검증
    const loginCheck = async () => {
        // 유효성 검사
        if(email === null || email === '') {
            alert('아이디를 입력 하세요');
            emailRef.current.focus();
            return false;

        }
        if(password === null || password ==='') {
            alert('패스워드를 입력 하세요.');
            passwordRef.current.focus();
            return false;
        }

        let formData = new FormData();
        formData.append("email", email);
        formData.append("password", password);
        
        await fetch('http://localhost:8080/restapi/loginCheck?autologin=NEW', {
            method: 'POST',
            body: formData
        }).then((response) => response.json())
            .then((data) => {
                if(data.message === 'good') {
                    cookieManage(data.username, data.role, data.authkey);
                    <Navigate to='/board/list?page=1' />
                } else if(data.message === 'ID_NOT_FOUND') {
                    setMessage('존재하지 않는 이메일입니다.');
                } else if(data.message === 'PASSWORD_NOT_FOUND') {
                    setMessage('잘못된 패스워드입니다.');
                } else {
                    console.log("message = " + data.message);
                    alert("시스템 장애로 로그인이 실패 했습니다.");		 
                }		  
       }).catch((error)=> { console.log("error = " + error);} );
    }

    const cookieManage = (username, role, authkey) => {
        // email 쿠키 등록
        if(rememberEmailRef.current.checked) {
			document.cookie = 'email=' + email + ';path=/; expires=Sun, 31 Dec 2023 23:59:59 GMT';
		} else document.cookie = 'email=' + email + ';path=/; max-age=0';
		
        // password 쿠키 등록
		if(rememberPasswordRef.current.checked) {
			
			//Base64(양방향 복호화)로 패스워드 인코딩
			const key = CryptoJS.enc.Utf8.parse(password);
		    const base64 = CryptoJS.enc.Base64.stringify(key);
			setPassword(base64);
		
			document.cookie = 'password=' + password + ';path=/; expires=Sun, 31 Dec 2023 23:59:59 GMT';
		} else document.cookie = 'password=' + password + ';path=/; max-age=0';	

        if(rememberMeRef.current.checked){			
			document.cookie = 'authkey=' + authkey + ';path=/; expires=Sun, 31 Dec 2023 23:59:59 GMT';
		} else document.cookie = 'authkey=' + authkey + ';path=/; max-age=0';

            document.cookie = 'username=' + username + ';path=/; expires=Sun, 31 Dec 2023 23:59:59 GMT'
            document.cookie = 'role=' + role + ';path=/; expires=Sun, 31 Dec 2023 23:59:59 GMT'
    }

    return (
        <div className='main'>
                <div>
                    <img src={Logo} alt="서울기술교육센터" />
                </div>
                
                <div className="login">
                    <h1>로그인</h1>
                        <input type="text" ref={emailRef} value={email} className="email"
                            onChange={(e) => setEmail(e.target.value)} placeholder="이메일을 입력하세요." />
                        <input type="password" ref={passwordRef} value={password} className="memberpasswd" 
                            onChange={(e) => setPassword(e.target.value)} placeholder="비밀번호를 입력하세요." /> 
                        <p id="msg" style={{color:'red',textAlign:'center'}}> {message}</p> 
                        <br/>
                        <input type="checkbox" ref={rememberEmailRef} onChange={(e) => checkRememberEmail(e)} />이메일 기억
                        <input type="checkbox" ref={rememberPasswordRef} onChange={(e) => checkRememberPassword(e)}/>패스워드 기억
                        <input type="checkbox" ref={rememberMeRef} onChange={(e) => checkRememberMe(e)}/>자동 로그인
                        | <a href="/oauth2/authorization/google">구글계정으로 로그인</a>
                        <br/>
                        {/* <input type="button" className="login_btn" value="로그인" onClick={ loginCheck()}/>  */}
                        <div className="bottomText">
                            사용자가 아니면 ▶<Link to="/member/signup">여기</Link>를 눌러 등록을 해주세요.<br/><br/>
                            [<a href="/member/searchID" >아이디</a> | 
                            <a href="/member/searchPassword" >패스워드</a>  찾기]  <br/><br/>    
                        
                        </div>
                </div>
        </div>
        
    );
}

export default Login;