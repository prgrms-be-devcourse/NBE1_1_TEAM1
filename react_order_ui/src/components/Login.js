import '../Login.css';
import React, { useState } from "react";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export function Login({ onLoginSuccess }) {
    const [isSignUp, setIsSignUp] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSignUpClick = () => {
        setIsSignUp(true);
    };

    const handleSignInClick = () => {
        setIsSignUp(false);
    };

    const handleSignIn = async (e) => {
    e.preventDefault();
    try {
        const response = await axios.post('http://localhost:8080/api/v1/login', {
            username,
            password
        });

        // 응답 상태 코드가 200대인 경우 Authorization 헤더 추출
        if (response.status >= 200 && response.status < 300) {
            const authToken = response.headers['authorization'] || response.headers['Authorization'];
            alert("로그인 성공")
            
            localStorage.setItem('token', authToken); // 토큰을 로컬 스토리지에 저장
            axios.defaults.headers.common['Authorization'] = authToken; // 기본 헤더에 토큰 설정
            navigate("/main")
        } else {
            console.error('응답 상태 코드 오류:', response.status);
            throw new Error('로그인에 실패했습니다. 응답 상태 코드 오류.');
        }
    } catch (error) {
        console.error('로그인 실패:', error);
        alert('로그인에 실패했습니다. 사용자 이름과 비밀번호를 확인하세요.');
    }
};

    const handleSignUp = async (e) => {
        e.preventDefault();
        try {
            await axios.post('http://localhost:8080/api/v1/join', {
                username,
                password,
                role: 'USER' // Fixed role value
            });
            alert('회원가입 성공!');
            setIsSignUp(false);
            navigate('/main'); // Redirect to login page after sign up
        } catch (error) {
            console.error('Sign Up failed:', error);
            alert('회원가입에 실패했습니다. 다시 시도해주세요.');
        }
    };

    return (
        <div className={`container ${isSignUp ? "right-panel-active" : ""}`} id="container">
            <div className="form-container sign-up-container">
                <form onSubmit={handleSignUp} className="login-form">
                    <h1>Create Account</h1>
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                    <input
                        type="password"
                        className="form-control"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                    <button type="submit">Sign Up</button>
                </form>
            </div>
            <div className="form-container sign-in-container">
                <form onSubmit={handleSignIn} className="login-form">
                    <h1>Sign In</h1>
                    <div className="form-floating">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="아이디 입력..."
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                        <label htmlFor="username">아이디</label>
                    </div>
                    <div className="form-floating">
                        <input
                            type="password"
                            className="form-control"
                            placeholder="비밀번호 입력..."
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                        <label htmlFor="password">비밀번호</label>
                    </div>
                    <button type="submit">Sign In</button>
                </form>
            </div>
            <div className="overlay-container">
                <div className="overlay">
                    <div className="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <button className="ghost" onClick={handleSignInClick}>Sign In</button>
                    </div>
                    <div className="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <button className="ghost" onClick={handleSignUpClick}>Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;