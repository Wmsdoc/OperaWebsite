/**
 * 戏曲音频
 */
export interface OperaAudio {
	/**
	 * 戏曲编号
	 */
	audioId?: string
	/**
	 * 戏曲简介
	 */
	audioInfo?: string
	/**
	 * 类型编号
	 */
	typeId?: string
	/**
	 * 戏曲音频下载量
	 */
	downloadNum?: number
	/**
	 * 戏曲音频下载路径
	 */
	downloadUrl?: string
	/**
	 * 文件名称
	 */
	filename?: string
	/**
	 * 是否审核
	 */
	isExamine?: number
	/**
	 * 是否删除 （默认0 未删除 1 已删除）
	 */
	isDelete?: number
	/**
	 * 创建时间
	 */
	createdAt?: Date
	/**
	 * 更新时间
	 */
	updatedAt?: Date
	/**
	 * 创建人
	 */
	createdBy?: number
	/**
	 * 修改人
	 */
	updatedBy?: number
}

export interface OperaAudioVO {
	/**
	 * 戏曲编号
	 */
	audioId?: string
	/**
	 * 戏曲简介
	 */
	audioInfo?: string
	/**
	 * 创建时间
	 */
	createdAt?: Date
	/**
	 * 创建人
	 */
	createdBy?: number
	/**
	 * 戏曲音频下载量
	 */
	downloadNum?: number
	/**
	 * 戏曲音频下载路径
	 */
	downloadUrl?: string
	/**
	 * 文件名称
	 */
	filename?: string
	/**
	 * 用户头像
	 */
	playgoerAvatar?: string
	/**
	 * 用户昵称
	 */
	playgoerName?: string
	/**
	 * 用户编号
	 */
	playgoerId?: string
	/**
	 * 用户简介
	 */
	playgoerInfo?: string
	/**
	 * 戏曲类型
	 */
	typeName?: string
	/**
	 * 更新时间
	 */
	updatedAt?: Date
}
