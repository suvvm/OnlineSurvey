<template>
	<view>
		<van-swipe :autoplay="3000" indicator-color="blue" :height="260">
			<van-swipe-item class="swipeItem" v-for="(image, key) in images" :key="key">
				<img :src="image"/>
			</van-swipe-item>
		</van-swipe>
		
		<van-grid class="grid">
		  <van-grid-item class="gridItem" icon="clock-o" text="今日问卷" @click="toTodayInv()"/>
		  <van-grid-item class="gridItem" icon="gold-coin-o" text="全部问卷" @click="toAllInv()"/>
		  <van-grid-item class="gridItem" icon="todo-list-o" text="历史"  @click="toHistory()"/>
		  <van-grid-item class="gridItem" icon="share" text="分享" @click="share()"/>
		</van-grid>
		<van-panel title="为我推荐">
			<view v-if="haveRcommendInv">
				<van-cell v-for="(item, key) in recommendInv" v-bind:key="key" :title="item.name" :value="item.statusText" size="large" :label="item.description" clickable @click="toInvDetails(key)" />
			</view>
			<view v-else>
				<view>暂无推荐</view>
			</view>
		</van-panel>
		<!-- 底部导航栏 -->
		<van-tabbar route>
		  <van-tabbar-item icon="home-o" to="/pages/home/home">首页</van-tabbar-item>
		  <van-tabbar-item icon="search" to="/pages/search/search">检索</van-tabbar-item>
		  <van-tabbar-item icon="user-o" to="/pages/user/user">我的</van-tabbar-item>
		</van-tabbar>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				images: [
					'/static/swipeImgs/suvvm.png',
					'/static/swipeImgs/suvvm2.png',
					'/static/swipeImgs/suvvm3.png'
				],
				userInfo: {tags:[]},
				recommendInv: [],
				haveRcommendInv: false
				
			}
		},
		onLoad() {
			this.$toast.loading({
				duration: 0,	// 持续展示 toast
				forbidClick: true,	// 禁用背景点击
				message: '加载中'
			});
			var rp = require('request-promise');
			var options = {	// 根据id获取用户包括头像与人脸数据的全部信息
			    method: 'POST',
			    uri: 'http://localhost:8080/getUserById',
			    form: {
			        id: this.$cookies.get("userInfo").id,
			    }
			};
			
			rp(options).then(res => {	// 请求成功
				this.$toast.clear();
				if(res == "error") {
					this.$toast.fail('获取用户信息识别，未找到用户信息');
				} else {
					this.userInfo = JSON.parse(res);
					// console.log(this.userInfo);
					// this.$toast.success('加载成功');
					var rpRecommend = require('request-promise');
					var optionsRecommend = {	// 根据id获取用户喜爱tag包含问卷
					    method: 'POST',
					    uri: 'http://localhost:8080/getRecommendByTag',
					    form: {
					        tagList: JSON.stringify(this.userInfo.tags),
					    }
					};
					rpRecommend(optionsRecommend).then(resRecommend =>{
						this.$toast.success('加载成功');
						if(resRecommend != null){
							this.recommendInv = JSON.parse(resRecommend);
							console.log(this.recommendInv);
							for(var i = 0; i < this.recommendInv.length; i++){
								if(this.recommendInv[i].visible){
									this.haveRcommendInv = true;
								}
							}
						}
					}).catch(errRecommend =>{
						this.$toast.success('加载成功');
					});
					
				}
				
			}).catch(err => {	// 请求失败
				this.$toast.clear();
				this.$toast.fail('获取用户信息, 请检查网络连接');
				console.log(err);
			});
			
		},
		methods: {
			share() {
			        //其中915269675指的是QQ号码 可以动态获取
			        window.location.href="mqqwpa://im/chat?chat_type=wpa&uin=915269675&version=1&src_type=web&web_src=oicqzone.com";
			},
			toTodayInv() {
				this.$router.push({
					path:'/pages/home/todayInv'
				})
			},
			toAllInv() {
				this.$router.push({
					path:'/pages/home/allInv'
				})
			},
			toHistory() {
				this.$router.push({
					path:'/pages/home/history'
				})
			},
			toInvDetails(key) {
				this.$router.push({
					path: '/pages/investigates/involvedInv', 
					query: {
						invDetailsId: this.recommendInv[key].id
					}
				});
			}
		}
	}
</script>

<style>
	.grid{
		height: 170rpx;
	}
	
</style>
