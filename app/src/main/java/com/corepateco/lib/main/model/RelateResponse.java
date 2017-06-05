package com.corepateco.lib.main.model;

import java.util.List;

/**
 * Created by TelcomTV on 12/23/2016.
 */

public class RelateResponse {

    /**
     * total : 5
     * items : [{"id":"c8e0e504-95f8-4ba6-88a3-5d024bcd39bf","slug":null,"type":"clip","title":"Ai sẽ giúp đỡ người đi đường bị ngất xỉu?","description":"Trên một cung đường ở vùng quê nếu một người đi xe máy bỗng dung chóng mặt ngất xỉu người cầm lái chắc chắn phải nhờ người qua đường giúp đỡ, nếu bạn gặp tình huống đó trên đường bạn sẽ làm gì?\r\n","shortDescription":null,"imgBanner":null,"imgPortrait":"http://mw.pateco-cdn.com/images/posters/1053_d1b2af040c7f8250799b2894e4da2a12_20160929061205_big.jpg","imgLandscape":null,"subTitle":null,"profile":"hd","duration":12,"releaseDate":null,"imdb":"0","parentId":"0415639f-ed5b-4b8a-820d-dfe17fe23798","totalChild":0,"currentChild":0,"order":0,"vendor":"Mobitv","relativeId":1053},{"id":"3651462c-c218-455d-be5e-9baa631f1f8c","slug":"ai-san-sang-cuu-giup-co-gai-sap-bi-o-to-chen-qua-nguoi","type":"clip","title":"Ai sẵn sàng cứu giúp cô gái sắp bị ô tô chèn qua người?","description":"Một chiếc xe ô tô lùi nhưng do lái xe không quan sát kỹ phía sau có người bị ngã nên vẫn tiếp tục lùi xe. Những người đi đường chứng kiến cảnh tượng cô gái đang gặp nguy hiểm trong gang tấc sẽ can thiệp giúp đỡ cô như thế nào? \r\n","shortDescription":null,"imgBanner":null,"imgPortrait":"http://mw.pateco-cdn.com/images/posters/1079_3c6934d7397613f71ed22572aefae73c_20160929062238_big.jpg","imgLandscape":null,"subTitle":null,"profile":"hd","duration":15,"releaseDate":null,"imdb":"0","parentId":"0415639f-ed5b-4b8a-820d-dfe17fe23798","totalChild":0,"currentChild":0,"order":1,"vendor":"Mobitv","relativeId":1079},{"id":"823bb468-ea6d-4766-9506-17fdeaa43ef6","slug":"ai-dam-giup-nguoi-vo-bi-chong-danh-dap","type":"clip","title":"Ai dám giúp người vợ bị chồng đánh đập?","description":"Bạn đi ngang qua đường thì bắt gặp một tình huống bạo lực gia đình, người vợ bị người chồng đánh đập thì bạn sẽ làm gì? Nếu bắt gặp tình huống trên phản ứng của bạn và mọi người sẽ như thế nào?\r\n","shortDescription":null,"imgBanner":null,"imgPortrait":"http://mw.pateco-cdn.com/images/posters/1033_246a445803ab420053322a622dc68d54_20160929060645_big.jpg","imgLandscape":null,"subTitle":null,"profile":"hd","duration":14,"releaseDate":null,"imdb":"0","parentId":"0415639f-ed5b-4b8a-820d-dfe17fe23798","totalChild":0,"currentChild":0,"order":2,"vendor":"Mobitv","relativeId":1033},{"id":"24ddfc5f-1ad2-4688-a392-f4698b31b79c","slug":"hot-boy-tay-di-ban-ao-dao-de-kiem-them","type":"clip","title":"Hot boy Tây đi bán áo dạo để \u201ckiếm thêm\u201d","description":"Hải Phòng, thành phố náo nhiệt và mạnh mẽ là một trong những trung tâm du lịch lớn của Việt Nam. Trong chương trình camera giấu kín lần này chàng tây Adrian sẽ đến với chảo lửa Lạch Tray trong trận tranh tài giữa Hải Phòng và Bình Dương. Vừa là cổ động viên cuồng nhiệt vừa bán áo thể thao, công việc của Adrian xem ra hứa hẹn rất nhiều điều thú vị.","shortDescription":null,"imgBanner":null,"imgPortrait":"http://mw.pateco-cdn.com/images/posters/1000_d25f62b0c890c70078547ecd9ea91827_20160929054856_big.jpg","imgLandscape":null,"subTitle":null,"profile":"hd","duration":15,"releaseDate":null,"imdb":"0","parentId":"0415639f-ed5b-4b8a-820d-dfe17fe23798","totalChild":0,"currentChild":0,"order":3,"vendor":"Mobitv","relativeId":1000},{"id":"938805b8-78ee-4c68-83f2-92352b1c96d9","slug":"lam-gi-khi-bi-ga-chi-pheo-quay-roi","type":"clip","title":"Làm gì khi bị gã \u201cchí phèo\u201d quấy rối?","description":"Sau những cuộc nhậu là hậu quả nhiều người rơi vào trạng thái say xỉn, say rượu khiến người ta không làm chủ được bản thân gây ảnh hưởng không tốt đến bản thân, gia đình và xã hội. Liệu rằng khi gặp một người say ngoài đường thì phản ứng của mọi người sẽ như thế nào?\r\n","shortDescription":null,"imgBanner":null,"imgPortrait":"http://mw.pateco-cdn.com/images/posters/1070_b230fe0151f802b4eab752ed8c1150ba_20160929061652_big.jpg","imgLandscape":null,"subTitle":null,"profile":"hd","duration":15,"releaseDate":null,"imdb":"0","parentId":"0415639f-ed5b-4b8a-820d-dfe17fe23798","totalChild":0,"currentChild":0,"order":4,"vendor":"Mobitv","relativeId":1070}]
     */

    private int total;
    private List<ItemsBean> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }


}
