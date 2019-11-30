<template>
	<view>
		<van-cell v-for="(item, key) in userInfo.investigates" v-bind:key="key" :title="item.name" :value="item.id" size="large" :label="item.description" clickable @click="cmpInvDetails(key)" />
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userInfo: {}
			}
		},
		onLoad() {
			this.$toast.loading({
				duration: 0,	// 持续展示 toast
				forbidClick: true,	// 禁用背景点击
				message: '加载中'
			});
			
			var rp = require('request-promise');
			var options = {
			    method: 'POST',
			    uri: 'http://localhost:8080/getUserById',
			    form: {
			        id: this.$cookies.get("userInfo").id,
			    }
			};
			rp(options).then(res => {
				this.$toast.clear();
				if(res == "error") {
					this.$toast.fail('获取用户信息识别，未找到用户信息');
				} else {
					this.userInfo = JSON.parse(res);
					console.log(this.userInfo);
					this.$toast.success('获取用户信息成功');
				}
				// console.log(this.note.backgroundImage)
			}).catch(err => {
				this.$toast.clear();
				this.$toast.fail('获取用户信息, 请检查网络连接');
				console.log(err);
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
			cmpInvDetails(key) {
				this.$router.push({
					path: '/pages/investigates/mdfInv', 
					query: {
						invId: this.userInfo.investigates[key].id
					}
				});
			}
		}
	}
</script>

<style>
</style>
