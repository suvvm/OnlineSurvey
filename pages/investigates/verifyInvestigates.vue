<template>
	<view>
		<view v-if="(invList.length - (currentPage * 5 - 5)) < 5">
			<van-cell v-for="item of invList.length % 5" v-bind:key="item" :title="invList[item+listBg - 1].name" :value="invList[item+listBg-1].id" size="large" :label="invList[item+listBg-1].description" clickable @click="verifyInvDetails(item+listBg-1)" />
		</view>
		<view v-else>
			<van-cell v-for="item of 5" v-bind:key="item" :title="invList[item+listBg - 1].name" :value="invList[item+listBg-1].id" size="large" :label="invList[item+listBg-1].description" clickable @click="verifyInvDetails(item+listBg-1)" />
		</view>

		<!-- <van-cell  v-if="(invList.length - (currentPage * 5 - 5)) < 5"  v-for="item of invList.length % 5" v-bind:key="item" >123</van-cell> -->
		<van-pagination  v-model="currentPage" :total-items="invList.length" :items-per-page="5" @change="onPaginationChanged()"/>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				invList: [{"name":"asd"}],
				listBg: 0,
				currentPage: 1
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
			var rp = require('request-promise');
			// 获取所有tag
			rp('http://localhost:8080/getInvisibleInv').then(res => {
				// 获取所有Tag成功
				this.invList = JSON.parse(res);
				console.log(this.invList);
				this.$toast.clear();
				this.$toast.success('成功');
			}).catch(err => {
				// 获取失败
				console.log(err);
				this.$toast.clear();
				this.$toast.fail('加载失败，请检查网络连接');
			});
		},
		methods: {
			verifyInvDetails(key) {
				this.$router.push({
					path: '/pages/investigates/verifyInvDetails', 
					query: {
						invDetailsId: this.invList[key].id
					}
				});
			},
			onPaginationChanged() {
				this.listBg = this.currentPage * 5 - 5;
			}
		}
	}
</script>

<style>
</style>
