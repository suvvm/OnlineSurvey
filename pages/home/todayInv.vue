<template>
	<view>
		<view v-if="todayInv.length != 0">
			<view v-if="(todayInv.length - (currentPage * 5 - 5)) < 5">
				<van-cell v-for="item of todayInv.length % 5" is-link  v-bind:key="item" :title="todayInv[item+listBg - 1].name" size="large" :label="todayInv[item+listBg-1].description" clickable @click="toInvDetails(item+listBg-1)" />
			</view>
			<view v-else>
				<van-cell v-for="item of 5" v-bind:key="item" is-link  :title="todayInv[item+listBg - 1].name" size="large" :label="todayInv[item+listBg-1].description" clickable @click="verifyInvDetails(item+listBg-1)" />
			</view>
			<van-pagination  v-model="currentPage" :total-items="todayInv.length" :items-per-page="5" @change="onPaginationChanged()"/>
		</view>
		<view v-else>
			<view class="content">
				<view>今日暂无新问卷</view>
				<image class="sad" src="../../static/sadPanda.png"></image>
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				investigates: [{name:""}],
				todayInv: [],
				currentPage: 1,
				listBg: 0,
				todayS: ""
			}
		},
		onLoad() {
			this.$toast.loading({
				duration: 0,	// 持续展示 toast
				forbidClick: true,	// 禁用背景点击
				message: '加载中'
			});
			var rp = require('request-promise');
			rp('http://localhost:8080/getVisibleInv').then(res => {	// 请求成功
				this.$toast.clear();
				if(res == "error") {
					this.$toast.fail('获取问卷失败，未找到问卷信息');
				} else {
					var today = new Date();
					today.setTime(today.getTime());
					this.todayS = today.getFullYear()+"-" + (today.getMonth()+1) + "-" + today.getDate();
					this.investigates = JSON.parse(res);
					// console.log(this.todayS);
					var len = 0;
					for(var i = 0; i < this.investigates.length; i ++) {
						if(this.todayS == this.dateFormatDay(this.investigates[i].time)){
							this.todayInv.push(this.investigates[i]);
						}
					}
					console.log(this.todayInv);
					this.$toast.success('加载成功');
				}
				// console.log(this.note.backgroundImage)
			}).catch(err => {	// 请求失败
				this.$toast.clear();
				this.$toast.fail('获取问卷信息, 请检查网络连接');
				console.log(err);
			});
		},
		methods: {
			onPaginationChanged() {
				this.listBg = this.currentPage * 5 - 5;
			},
			dateFormatDay(longTypeDate){  
				var dateType = "";  
				var date = new Date();  
				date.setTime(longTypeDate); 
				dateType = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDay()+1); //yyyy-MM-dd格式日期
				return dateType;
			},
			toInvDetails(key) {
				this.$router.push({
					path: '/pages/investigates/involvedInv', 
					query: {
						invDetailsId: this.todayInv[key].id
					}
				});
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	.sad {
		height: 600rpx;
		width: 600rpx;
		margin-top: 100rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}
</style>
