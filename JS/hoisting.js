let age =20;

function showAge() {
    //console.log(age);
    let age = 30;
    // 이 블록 안의 let age가 선언 되었기 때문에 밖에있는 age변수가 사용이 안됨 -> TDZ 때문에 선언하기 전에는 사용 불가능

}

showAge();

//var 함수 스코프 --> var로 하나 만들면 거의 전역변수
// let const 블록 스코프 --> 지역변수 , 블록을 벗어나면 사용 못함

function makeAdder(x) {
    return function(y) {
        return x+y;
    }
}

const add3 = makeAdder(3);
console.log(add3(2))

function Item(title, price) {

}
// function add(...numver) {
//     numbers.forEach((num) => (result += num));
//     console.log(result);
// }

// add(1,2,3);


// 나머지 매개변수
// user 객체를 만들어 주는 생성자 함수를 만든다

function User(name, age, ...skills) {
    this.name =name;
    this.age = age;
    this.skills = skills;
}

const user1 = new User("Mkie", 30, "html", "css");
const user2 = new User("Tom", 20, "JS", "React");
const user3 = new User("Jane", 10, "English");
//console.log(user1, user2, user3);


// 전개구문
// arr1을 [4,5,6,1,2,3] 으로

let arr1 = [1,2,3];
let arr2 = [4,5,6];

// arr2.reverse().forEach((num) => {
//     arr1.unshift(num);
// });

arr1 = [...arr2, ...arr1];

console.log(arr1);