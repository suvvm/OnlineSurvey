<template>
	<view>
		<h3 class="title">{{invName}}</h3>
		<van-panel :title="invName" :desc="invDescription">
			<view v-for="(item,key) in invDetails" :key="key">
				<van-panel v-if="item.type == 0"  :title="item.stem" status="选择题">
					<!-- <view v-for="(opt,num) in item.options" :key="num">
						<van-field :value="opt.value" :label="opt.option" disabled/>
					</view> -->
					<van-checkbox-group v-model="result.resultlist[key]" :max="1">
					  <van-cell-group>
					    <van-cell v-for="(opt,num) in item.options" clickable :key="num" :title="opt.value">
					      <van-icon v-if="opt.value == ans[key].res.value" slot="right-icon" name="success" style="line-height: inherit;"/>
					    </van-cell>
					  </van-cell-group>
					</van-checkbox-group>
				</van-panel>
				<van-panel v-else-if="item.type == 1"  :title="item.stem" status="简答题">
					<van-field disabled v-model="ans[key].res.value" rows="3" autosize label="请作答" type="textarea" placeholder="请输入本题回答"/>
				</van-panel>
			</view>
		</van-panel>
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
				result: {resultlist:[]},
				ans: []
			}
		},
		onLoad() {
			this.$toast.loading({
				duration: 0,	// 持续展示 toast
				forbidClick: true,	// 禁用背景点击
				message: '加载中'
			});
			
			// console.log(this.$route);
			var rp = require('request-promise');
			var options = {
			    method: 'POST',
			    uri: 'http://localhost:8080/getInvestigate',
			    form: {
			        id: this.$route.query.invDetailsId
					// id: 300000002
			    }
			};
			var optionsuser = {
			    method: 'POST',
			    uri: 'http://localhost:8080/getUserById',
			    form: {
			        id: this.$cookies.get("userInfo").id,
			    }
			};
			rp(options).then(res => {
				// 获取id对应问卷
				var investigate = JSON.parse(res)[0];
				// console.log(investigate);
				this.invId = investigate.id;
				this.invName = investigate.name;
				this.invDetails = JSON.parse(investigate.details);
				for(var i = 0; i < this.invDetails.length; i++){
					this.invDetails[i].answer = "";
					this.result.resultlist[i] = [];
				}
				// console.log(this.invDetails);
				this.invDescription = investigate.description;
				this.$toast.clear();
				this.$toast.success('成功');
			}).catch(err => {
				// 获取失败
				console.log(err);
				this.$toast.clear();
				this.$toast.fail('加载失败，请检查网络连接');
			});
			
			rp(optionsuser).then(res => {	// 请求成功
				this.$toast.clear();
				if(res == "error") {
					this.$toast.fail('获取用户信息识别，未找到用户信息');
				} else {
					this.userInfo = JSON.parse(res);
					console.log(this.userInfo);
					for(var i = 0; i < this.userInfo.results.length; i++){
						if(this.userInfo.results[i].iid == this.$route.query.invDetailsId) {
							this.ans = JSON.parse(this.userInfo.results[i].ans);
						}
					}
					console.log(this.ans);
					this.$toast.success('获取用户信息成功');
				}
				// console.log(this.note.backgroundImage)
			}).catch(err => {	// 请求失败
				this.$toast.clear();
				this.$toast.fail('获取用户信息, 请检查网络连接');
				console.log(err);
			});
			
		},
		methods: {
			
		}
	}
</script>

<style>
</style>
