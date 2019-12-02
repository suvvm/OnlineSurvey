<template>
	
	<view>
	<view>66</view>
	<div id="myChart" :style="{width: '100%', height: '300px'}"></div>

	</view>
</template>

<script>
	export default {
		data () {
			return {
				
			}
		},
		mounted(){
			this.drawLine();
		},
		methods: {
			drawLine(){
				// 基于准备好的dom，初始化echarts实例
				let myChart = this.$echarts.init(document.getElementById('myChart'),null,{ renderer: 'svg' })
				// 绘制图表
				// myChart.init(document,null,{ renderer : 'svg' });
				myChart.setOption({
					title: { text: '在Vue中使用echarts' },
					tooltip: {},
					xAxis: {
						data: ["zjp","hjt","jzm","ysk","lxn","lsq"]
					},
					yAxis: {},
					series: [{
						name: 'nb',
						type: 'bar',
						data: [5, 20, 36, 10, 10, 20]
					}]
				});
			}
		}
	}
</script>

<style>

</style>
