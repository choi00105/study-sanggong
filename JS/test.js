let a = "\"hello world!!!\"";

console.log(a);

let b = `hello world!!!`;
console.log(b);

let c = `Everyone`;
let d = `Hello ${c}`
console.log(d)

const cdc = {
    userid: "xxx",
    username: "치대훈",
    sayHello: function(){
        console.log("Hello~");
    }
}

let cdc1 = cdc;
console.log("userid = " + cdc1.userid);
cdc1.sayHello();

const str = `{ 
    "name": "홍길동", 
    "age": 25, 
    "married": false, 
    "family": { "father": "홍판서", "mother": "춘섬" },
    "hobbies": ["독서", "음악감상"],
    "jobs": "강도" 
  }`; 
  console.log(str);
  const obj = JSON.parse(str); // JSON을 JS 객체로 전환
  console.log(obj.name + "," + obj.age);

  