import React from 'react';

// order 받아와서 표시하고 버튼 클릭시 돌아가게끔
export function OrderCompletion({ order, onReturnHome }) {
    return (
        <div className="card">
            <div className="card-body">
                {/*h1~h6까지 제목 크기*/}
                <h2 className="card-title">주문 완료</h2>
                {/*p 하나의 문단*/}
                <p className="card-text">주문이 성공적으로 완료되었습니다.</p>
                <h5>주문 정보</h5>
                <p>이메일: {order.email}</p>
                <p>주소: {order.address}</p>
                <p>우편번호: {order.postcode}</p>
                <h5>주문 상품</h5>
                {/*ul 순서가 중요하지 않는 항목들을 그룹화하기 위함*/}
                <ul>
                    {order.items.map(item => (
                        // markdown에서 *(띄어쓰기) 하면 나오는 특수기호
                        <li key={item.productId}>
                            {item.productName} - {item.count}개
                        </li>
                    ))}
                </ul>
                {/*strong 굵게*/}
                <p><strong>총 금액: {order.totalPrice}원</strong></p>
                <button onClick={onReturnHome} className="btn btn-primary">홈으로 돌아가기</button>
            </div>
        </div>
    );
}