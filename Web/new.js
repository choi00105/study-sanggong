function embos_image() {
    // (중요!) 출력 영상의 크기를 결정 --> 알고리즘에 따름.
    outH = inH;
    outW = inW;
    // 출력 이미지 메모리 할당.
    outImage = new Array(outH);
    for(var i=0; i<outH; i++)
        outImage[i] = new Array(outW);
    // ** 진짜 영상처리 알고리즘 구현 **
    // 짱! 중요
    var mask = [    [ -1.0 ,  0.0 ,  0.0 ], 
                    [  0.0 ,  0.0 ,  0.0 ], 
                    [  0.0 ,  0.0 ,  1.0 ]  ];
    // 임시 입력 배열 (입력배열+2) ==> 실수 처리
    var tmpInImage = new Array(inH+2);
    for(var i=0; i<inH+2; i++)
        tmpInImage[i] = new Array(inW+2);
    // 임시 입력 초기화 (127) --> 평균값? --> 정말로 한줄도 못참는다. (가장자리 가까운 값으로..)
    for(var i=0; i<inH+2; i++) 
        for (var k=0; k<inW+2; k++) 
            tmpInImage[i][k] = 127.0;
    // 입력 배열 --> 임시 입력 배열의 가운데 쏙~ 
    for(var i=0; i<inH; i++) 
        for (var k=0; k<inW; k++) 
            tmpInImage[i+1][k+1] = inImage[i][k];

    // 임시 출력 배열(출력배열과 동일) ==> 실수
    var tmpOutImage = new Array(outH);
    for(var i=0; i<outH; i++)
        tmpOutImage[i] = new Array(outW);
    //** 회선 연산 ** 마스크를 잡아서 전체를 긁으면서 계산하기...
    for(var i=0; i<inH; i++) {
        for (var k=0; k<inW; k++) {
            var S = 0.0;
            for(var m=0; m<3; m++) 
                for (var n=0; n<3; n++) 
                    S += tmpInImage[i+m][k+n] * mask[m][n];
            
            tmpOutImage[i][k] = S;
        }
    }
    // 후처리 : 마스크의 합계가 0일 경우.... (예외 있음)
    for (var i=0; i<outH; i++)
        for (var k=0; k<outW; k++)
            tmpOutImage[i][k] += 127.0;
    // 임시 출력 배열 --> 출력 배열
    for (var i=0; i<outH; i++)
        for (var k=0; k<outW; k++)
            outImage[i][k] = parseInt(tmpOutImage[i][k]);
    // *******************************
    displayImage();
}
function embos_image() {
    outH = inH;
    outW = inW;
    // 출력 이미지 할당
    outImage = new Array(outH);
    for(var i = 0; i<outH; i++) 
        outImage[i] = new Array(outW);
    // ** 진짜 영상처리 알고리즘 구현
    // +++ 마스크 (짱 중요)
    var mask = [    [-1.0, 0.0, 0.0],
                    [0.0, 0.0, 0.0],
                    [0.0, 0.0, 1.0] ];
    // 임시 입력 배열 (입력 배열 w h 각 +2)
    var tmpInImage = new Array(inH + 2);
    for(var i=0; i<inH + 2; i++)
        tmpInImage[i] = new Array(inW +2);
    // 임시 입력 초기화 : 127 -> 평균값?
    for(i = 0; i < inH+2; i++)
        for(k = 0; k <inW+2; k++) tmpInImage[i][k] = 127.0;
    // 입력 배열 -> 임시 입력 배열의 가운데로 
    for(i = 0; i < inH; i++)
        for(k = 0; k <inW; k++) tmpInImage[i+1][k+1] = inImage[i][k];
    // 임시 출력 배열 (출력 배열과 동일)
    var tmpOutImage = new Array(outH);
    for (var i = 0; i<outH; i++) tmpOutImage[i] = new Array(outW);
    // ** 회선 연산 **
    for(i = 0; i < inH; i++) {
        for(k = 0; k <inW; k++) {
            var S = 0.0;
            for(var m = 0; m<3; m++)
                for(var n =0; n<3; n++) S+= tmpInImage[i+m][k+n] * mask[m][n];
            tmpOutImage[i][k] = S;
        }
    }
    // 후처리
    for(var i =0; i < outH; i++)
        for(var k = 0; i < outW; i++)
            tmpOutImage[i][k] += 127.0;
    // 임시출력 배열 -> 출력 배열                 
    for(i = 0; i < outH; i++) {
        for(k = 0; k <outW; k++) {
            outImage[i][k] = parseInt(tmpOutImage[i][k]);
        }
    }
    // **************************
    displayImage();
}