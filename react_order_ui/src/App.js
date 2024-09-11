import './App.css';
import 'bootstrap/dist/css/bootstrap.css'
import React, {useEffect, useState} from "react";
import axios from "axios";

import {ProductList} from "./components/ProductList";
import {Summary} from "./components/Summary";
import {OrderCompletion} from "./components/OrderCompletion";


function App() {

    const [products, setProducts] = useState([
        {productId: 'uuid-1', productName: '콜롬비아 커피 1', category: '커피빈', price: 5000},
        {productId: 'uuid-2', productName: '콜롬비아 커피 2', category: '커피빈', price: 5000},
        {productId: 'uuid-3', productName: '콜롬비아 커피 3', category: '커피빈', price: 5000},
    ]);
    const [items, setItems] = useState([]);

    const [order, setOrder] = useState({ // 주문 정보 상태
        email: "",
        address: "",
        postcode: ""
    });

    const [completedOrder, setCompletedOrder] = useState(null);
    const [isOrderComplete, setIsOrderComplete] = useState(false);

    const handleAddClicked = productId => {
        const product = products.find(v => v.productId === productId);
        const found = items.find(v => v.productId === productId);
        const updatedItems =
            found ? items.map(v => (v.productId === productId) ? {...v, count: v.count + 1} : v) : [...items, {...product, count: 1}];
        setItems(updatedItems);
    }

    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/products')
            .then(v => setProducts(v.data))
    }, []);

    const handleOrderSubmit = (order) => {
        if (items.length === 0) {
            alert("아이템을 추가해 주세요!");
        } else {
            axios.post('http://localhost:8080/api/v1/orders', {
                email: order.email,
                address: order.address,
                postcode: order.postcode,
                orderItems: items.map(v => ({
                    productId: v.productId,
                    category: v.category,
                    price: v.price,
                    quantity: v.count
                }))
            }).then(
                () => {
                    // 주문완료 화면에 넘길 데이터를 저장시켜줘야 함
                    setCompletedOrder({
                        ...order,
                        items: items,
                        totalPrice: items.reduce((prev, curr) => prev + (curr.price * curr.count), 0)
                    });
                    alert("주문이 정상적으로 접수되었습니다.");
                    setItems([]);
                    setOrder({email: "", address: "", postcode: ""});
                    setIsOrderComplete(true);
                },
                e => {
                    alert("서버 장애");
                    console.error(e);
                })
        }
    }

    const handleReturnToHome = () => {
        // 아래 함수들을 호출해서 다시 홈 화면이 렌더링 되도록 유도
        // router-dom 도 많이 나오는데 일단 이렇게
        setIsOrderComplete(false);
        setCompletedOrder(null);
    }

    return (
        <div className="container-fluid">
            <div className="row justify-content-center m-4">
                <h1 className="text-center">Grids & Circle</h1>
            </div>
            {!isOrderComplete ? (
                <div className="card">
                    <div className="row">
                        <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
                            <ProductList products={products} onAddClick={handleAddClicked}/>
                        </div>
                        <div className="col-md-4 summary p-4">
                            <Summary items={items} order={order} setOrder={setOrder} onOrderSubmit={handleOrderSubmit}/>
                        </div>
                    </div>
                </div>
            ) : (
                // 결제하기 버튼 누를때 저장된 completeOrder를 다음 화면으로 넘겨줌
                // 현재 화면으로 돌아올수 있게 handleReturnToHome 을 보내면서 기본 세팅을 초기화 해줌
                <OrderCompletion order={completedOrder} onReturnHome={handleReturnToHome} />
            )}
        </div>
    );
}

export default App;
