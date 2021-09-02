$(document).ready(function () {

    // 報表測試 chart.js
	var datasource=document.getElementById("chart-data");
	var orderamount=datasource.getAttribute('data-orderamount');
	//去頭'[' 去尾']'
	var orderAmount=orderamount.substring(1,orderamount.length-1);
	//用逗號隔開轉陣列
	var orderAmount1=orderAmount.split(',');
//	var orders=datasource.getAttribute('data-orders');
//	console.log(orders);
    var label =datasource.getAttribute('data-orderduring');
	console.log(label);
	var label1=label.substring(1,label.length-1);
	console.log(label1);
	var labelarray=label1.split(',');
    var ctx1 = document.getElementById('myChart1');
    var myChart1 = new Chart(ctx1, {  
        data: {
            labels:labelarray,
            datasets: [{
            	type: 'bar',
                label: '日營業額(NT)',
                backgroundColor: 'rgb(255, 99, 132,0.2)',
                borderColor: 'rgb(255, 99, 132)',
                //DB資料! 
                data: orderAmount1,
//            },{
//            	type: 'line',
//                label: '日訂單數(筆)',
//                borderColor: 'rgb(54, 162, 235)',
//                //TODO:抓DB資料改寫到這即可!!! 
//                data: [1, 1, 1, 1, 2, 1, 1, 1, 1, 8, 4, 1, 1, 1],
            }
            ]
        }
    });
    // 熱門餐點圓餅圖
    const label2 = ['A餐', 'B餐', 'C餐', 'D餐', 'E餐'];
    var ctx2 = document.getElementById('myChart2');
    var myChart2 = new Chart(ctx2, {
        type: 'pie',
        data: {
            labels: label2,
            datasets: [{
                label: '熱門餐點',
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    // 'rgba(255, 159, 64, 0.2)',
                    'rgba(255, 205, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    // 'rgba(201, 203, 207, 0.2)'
                ],
                borderColor: [
                    'rgb(255, 99, 132)',
                    // 'rgb(255, 159, 64)',
                    'rgb(255, 205, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(54, 162, 235)',
                    'rgb(153, 102, 255)',
                    // 'rgb(201, 203, 207)'
                ],
                borderWidth: 1,
                //TODO:抓DB資料改寫到這即可!!! 
                data: [75, 50, 35, 20, 10],
                hoverOffset: 4
            }]
        }
    });

});
