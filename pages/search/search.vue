<template>
	<view>
		<van-search placeholder="请输入搜索关键词" v-model="searchValue" @search="onSearch()"/>
		<view v-if="ansInv.length != 0">
			<van-cell v-for="(item,key) in ansInv" is-link :key="key" :title="item.name" :label="item.description" clickable @click="toInvDetails(key)" />
		</view>
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
				searchValue: "",
				investigates: [],
				ansInv: []
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
					this.investigates = JSON.parse(res);
					console.log(this.investigates);
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
			onSearch() {
				this.ansInv = [];
				for(var i = 0; i < this.investigates.length; i++) {
					// console.log(this.investigates[i].description);
					// console.log(this.investigates[i].description.search(this.searchValue));
					if(this.investigates[i].description.search(this.searchValue) != -1){
						this.ansInv.push(this.investigates[i]);
						continue;
					}
					if(this.investigates[i].details.search(this.searchValue) != -1){
						this.ansInv.push(this.investigates[i]);
						continue;
					}
					if(this.investigates[i].name.search(this.searchValue) != -1){
						this.ansInv.push(this.investigates[i]);
						continue;
					}
					var flag = false;
					for(var j = 0; j < this.investigates[i].tags.length; j++){
						if(this.investigates[i].tags[j].description.search(this.searchValue) != -1){
							flag = true;
							break;
						}
						if(this.investigates[i].tags[j].name.search(this.searchValue) != -1){
							flag = true;
							break;
						}
					}
					if(flag){
						this.ansInv.push(this.investigates[i]);
						continue;
					}
				}
				console.log(this.ansInv);
				this.$forceUpdate();
			},
			toInvDetails(key) {
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
