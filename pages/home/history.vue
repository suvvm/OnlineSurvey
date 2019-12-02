<template>
	<view>
		<view v-if="(invList.length - (currentPage * 5 - 5)) < 5">
			<van-cell v-for="item of invList.length % 5" v-bind:key="item" :title="invList[item+listBg - 1].name" is-link :label="invList[item+listBg-1].description" clickable @click="toMyResult(item+listBg-1)" />
		</view>
		<view v-else>
			<van-cell v-for="item of 5" v-bind:key="item" :title="invList[item+listBg - 1].name" is-link :label="invList[item+listBg-1].description" clickable @click="toMyResult(item+listBg-1)" />
		</view>
		<!-- <van-cell  v-if="(invList.length - (currentPage * 5 - 5)) < 5"  v-for="item of invList.length % 5" v-bind:key="item" >123</van-cell> -->
		<van-pagination  v-model="currentPage" :total-items="invList.length" :items-per-page="5" @change="onPaginationChanged()"/>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userInfo: {},
				invList: [],
				currentPage: 1,
				listBg: 0
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
			var rpInv = require('request-promise');
			
			rp(options).then(res => {	// 请求成功
				this.$toast.clear();
				if(res == "error") {
					this.$toast.fail('获取用户信息识别，未找到用户信息');
				} else {
					this.userInfo = JSON.parse(res);
					// console.log(this.userInfo);
					
					var optionsInv = {	// 根据id获取用户包括头像与人脸数据的全部信息
						method: 'POST',
						uri: 'http://localhost:8080/getInvsByResults',
						form: {
							results: JSON.stringify(this.userInfo.results)
						}
					};
					rpInv(optionsInv).then(resResInv => {
						this.$toast.success('获取用户信息成功');
						this.invList = JSON.parse(resResInv);
						console.log(this.invList);
					}).catch(errResInv => {
						this.$toast.fail('不存在历史');
					});
				}
				// console.log(this.note.backgroundImage)
			}).catch(err => {	// 请求失败
				this.$toast.clear();
				this.$toast.fail('获取用户信息, 请检查网络连接');
				console.log(err);
			});
		},
		methods: {
			onPaginationChanged() {
				this.listBg = this.currentPage * 5 - 5;
			},
			toMyResult(key) {
				this.$router.push({
					path: '/pages/home/myResult', 
					query: {
						invDetailsId: this.invList[key].id
					}
				});
			}
		}
	}
</script>

<style>
</style>
