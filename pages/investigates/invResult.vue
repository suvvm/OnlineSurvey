<template>
	<view>
		<van-button type="info" size="large" @click="drawLine()">显示结果</van-button>
		
		
		<view v-for="(item,key) in details" :key="key">
			
			<van-panel v-if="item.type == 0"  :title="item.stem" status="选择题">
				<view v-for="(opt,num) in item.options" :key="num">
					<van-field :value="opt.value" :label="opt.option" disabled/>
				</view>
				<view :id="'myChart'+key" :style="{width: '100%', height: '300px'}"></view>
			</van-panel>
			
			<view v-else-if="item.type == 1">
				<van-panel :title="item.stem" status="简答题"></van-panel>
				<view v-for="(opt,num) in ans[key]" :key="num">
					
					<van-field v-if="show" :value="opt.value" disabled/>
				</view>
			</view>
		
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				investigate: {},
				details: [],
				ans: [],
				text: [],
				ansOptions: [],
				ansData: [],
				show: false
			}
		},
		onLoad() {
			this.$toast.loading({
				duration: 0,	// 持续展示 toast
				forbidClick: true,	// 禁用背景点击
				message: '加载中'
			});
			
			if(this.$cookies.get("userInfo") != null){	// 获取当前用户信息
				this.userInfo = this.$cookies.get("userInfo");
			}
			
			// console.log(this.$route);
			var rp = require('request-promise');
			var options = {
			    method: 'POST',
			    uri: 'http://localhost:8080/getInvestigate',
			    form: {
			        // id: this.$route.query.invId
					id: 300000002
			    }
			};
			rp(options).then(res => {
				// 获取id对应问卷
				this.investigate = JSON.parse(res)[0];
				this.details = JSON.parse(this.investigate.details);
				// console.log(this.investigate);
				// console.log(this.details);
				for(var i = 0; i < this.investigate.results.length; i++) {
					var ans = JSON.parse(this.investigate.results[i].ans);
					// console.log(ans);
					for(var j = 0; j < ans.length; j++){
						if(i == 0){
							this.text.push(ans[j].stem);
						}
						if(this.ans.length <= j){
							var tmp = [];
							tmp.push(ans[j].res);
							this.ans.push(tmp);
						} else {
							this.ans[j].push(ans[j].res);
						}
					}
				}
				
				for(var i = 0; i < this.details.length; i++) {
					this.ansOptions.push([]);
					this.ansData.push([]);
					if(this.details[i].options != null){
						for(var j = 0; j < this.details[i].options.length; j++) {
							this.ansOptions[i].push(this.details[i].options[j].value);
							this.ansData[i].push(0);
						}
					}
				}
				
				// console.log(this.ansOptions);
				// console.log("A".charCodeAt())
				for(var i = 0; i < this.ans.length; i++) {
					
					for(var j = 0; j < this.ans[i].length; j++){
						if( this.ans[i][j].option != null){
							var optval = this.ans[i][j].option.charCodeAt()-65;
							this.ansData[i][optval] += 1;
							
						}
					}
				}
				// console.log("ansData");
				// console.log(this.ansData);
				// console.log(this.ans);
				// console.log(this.text);
				
				this.$toast.clear();
				this.$toast.success('加载成功');
			}).catch(err => {
				// 获取失败
				console.log(err);
				this.$toast.clear();
				this.$toast.fail('加载失败，请检查网络连接');
			});
			
		},
		mounted(){
		},
		methods: {
			drawLine(){
				this.show = true;
				for(var i = 0; i <= this.text.length; i++) {
					// console.log(i);
					if(this.details[i].options != null){
						let myChart = this.$echarts.init(document.getElementById('myChart'+i),null,{ renderer: 'svg' });
						myChart.setOption({
							title: { text: this.text[i] },
							tooltip: {},
							xAxis: {
								data: this.ansOptions[i]
							},
							yAxis: {},
							series: [{
								name: 'nb',
								type: 'bar',
								data: this.ansData[i]
							}]
						});
					}
				}
				
				this.$forceUpdate();
			}
		}
	}
</script>

<style>
</style>
