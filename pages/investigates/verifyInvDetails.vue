<template>
	<view>
		<van-panel :title="invName" :desc="invDescription" :status="statusText">
			<view v-for="(item,key) in invDetails" :key="key">
				<van-panel v-if="item.type == 0"  :title="item.stem" status="选择题">
					<view v-for="(opt,num) in item.options" :key="num">
						<van-field :value="opt.value" :label="opt.option" disabled/>
					</view>
				</van-panel>
				<van-panel v-else-if="item.type == 1"  :title="item.stem" status="简答题">
				</van-panel>
			</view>
		</van-panel>
		<van-cell-group class="btnGup" v-if="!status">
			<van-button class="subBtn" type="primary" @click="acceptSubmit()">通过</van-button>
			<van-button class="subBtn" type="info" @click="rejectSubmit()" v-if="statusText == '已驳回'" disabled>驳回</van-button>
			<van-button class="subBtn" type="info" @click="rejectSubmit()" v-else>驳回</van-button>
		</van-cell-group>
		<van-cell-group class="btnGup" v-else>
			<van-button class="subBtn" type="primary" @click="acceptSubmit()" disabled>通过</van-button>
			<van-button class="subBtn" type="info" @click="rejectSubmit()" disabled>驳回</van-button>
		</van-cell-group>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				invId: 0,
				invName: "",
				invDetails: [],
				invDescription: "",
				userInfo: {},
				status: false,
				statusText: "等待审核"
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
			    uri: 'http://101.201.70.76:8211/getInvestigate',
			    form: {
			        id: this.$route.query.invDetailsId
					// id: 300000002
			    }
			};
			rp(options).then(res => {
				// 获取id对应问卷
				var investigate = JSON.parse(res)[0];
				// console.log(investigate);
				this.invId = investigate.id;
				this.invName = investigate.name;
				this.invDetails = JSON.parse(investigate.details);
				console.log(this.invDetails);
				this.invDescription = investigate.description;
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
			acceptSubmit() {
				var rp = require('request-promise');
				var options = {
				    method: 'POST',
				    uri: 'http://101.201.70.76:8211/verifyInv',
				    form: {
				        id: this.$route.query.invDetailsId,
						status: 1,
						email: this.userInfo.email,
						username: this.userInfo.username,
						ivnName: this.invName
				    }
				};
				rp(options).then(res => {
					this.status = true;
					this.statusText = "审核通过";
					this.$toast.clear();
					this.$toast.success('审核成功');
				}).catch(err => {
					// 审核失败
					console.log(err);
					this.$toast.clear();
					this.$toast.fail('审核失败，请检查网络连接');
				});
			},
			rejectSubmit() {
				var rp = require('request-promise');
				var options = {
				    method: 'POST',
				    uri: 'http://101.201.70.76:8211/verifyInv',
				    form: {
						id: this.$route.query.invDetailsId,
						status: 1,
						email: this.userInfo.email,
						username: this.userInfo.username,
						ivnName: this.invName
				    }
				};
				rp(options).then(res => {
					// 驳回成功
					this.statusText = "已驳回";
					this.$toast.clear();
					this.$toast.success('驳回成功');
				}).catch(err => {
					// 审核失败
					console.log(err);
					this.$toast.clear();
					this.$toast.fail('审核失败，请检查网络连接');
				});
			}
		}
	}
</script>

<style>
	.btnGup {
		text-align: center;
		margin-top: 30rpx;
	}
	.subBtn {
		width: 30%;
	}
</style>
