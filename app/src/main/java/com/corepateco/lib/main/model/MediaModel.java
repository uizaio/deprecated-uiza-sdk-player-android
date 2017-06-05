
package com.corepateco.lib.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MediaModel implements Parcelable {

    private int id;
    private String title;
    private String content;
    private String imageUrl;
    private String videoUrl;

    private MediaModel(
            final int id,
            final String title,
            final String content,
            final String imageUrl,
            final String videoUrl) {

        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
    }

    protected MediaModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        content = in.readString();
        imageUrl = in.readString();
        videoUrl = in.readString();
    }

    public static final Creator<MediaModel> CREATOR = new Creator<MediaModel>() {
        @Override
        public MediaModel createFromParcel(Parcel in) {
            return new MediaModel(in);
        }

        @Override
        public MediaModel[] newArray(int size) {
            return new MediaModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public static List<MediaModel> getPhotoModels() {
        List<MediaModel> mediaModels = new ArrayList<>();

        String titles[] = {
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
        };

        String contents[] = {
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
        };

        String urls[] = {
                "http://image.tmdb.org/t/p/w300_and_h450_bestv2/y31QB9kn3XSudA15tV7UWQ9XLuW.jpg",
                "https://ast.fimplus.io/images/5c8e24f1-3146-11e6-bcf1-73a7816e5f55.jpg",
                "https://ast.fimplus.io/images/42511700-b058-11e6-8c91-c91b3c8346c2.jpg",
                "https://ast.fimplus.io/images/5c8e24f1-3146-11e6-bcf1-73a7816e5f55.jpg",
                "https://ast.fimplus.io/images/42511700-b058-11e6-8c91-c91b3c8346c2.jpg",
                "https://ast.fimplus.io/images/5c8e24f1-3146-11e6-bcf1-73a7816e5f55.jpg",
                "https://ast.fimplus.io/images/42511700-b058-11e6-8c91-c91b3c8346c2.jpg",
                "https://ast.fimplus.io/images/5c8e24f1-3146-11e6-bcf1-73a7816e5f55.jpg",
        };

        for (int i = 0; i < titles.length; i++) {
            MediaModel mediaModel = new MediaModel(0, titles[i], contents[i], urls[i], "");
            mediaModels.add(mediaModel);
        }

        return mediaModels;
    }

    public static List<MediaModel> getVideoModels() {
        List<MediaModel> mediaModels = new ArrayList<>();

        String titles[] = {
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
        };

        String contents[] = {
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
        };

        String urls[] = {
                "http://image.tmdb.org/t/p/w185_and_h278_bestv2/hXRqbBilaYTUg8t10pJawynDFFB.jpg",
                "http://image.tmdb.org/t/p/w185_and_h278_bestv2/qKkFk9HELmABpcPoc1HHZGIxQ5a.jpg",
                "http://image.tmdb.org/t/p/w185_and_h278_bestv2/weUSwMdQIa3NaXVzwUoIIcAi85d.jpg",
                "http://image.tmdb.org/t/p/w185_and_h278_bestv2/2EUAUIu5lHFlkj5FRryohlH6CRO.jpg",
                "http://image.tmdb.org/t/p/w185_and_h278_bestv2/tHkjoAxmhp3Eau1h0Ir7maKMwUz.jpg",
                "http://image.tmdb.org/t/p/w185_and_h278_bestv2/hXRqbBilaYTUg8t10pJawynDFFB.jpg",
                "http://image.tmdb.org/t/p/w185_and_h278_bestv2/uPxtxhB2Fy9ihVqtBtNGHmknJqV.jpg",
                "http://image.tmdb.org/t/p/w185_and_h278_bestv2/hmOzkHlkGvi8x24fYpFSnXvjklv.jpg",
        };

        String videoUrls[] = {
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
        };

        for (int i = 0; i < titles.length; i++) {
            MediaModel mediaModel = new MediaModel(
                    0, titles[i], contents[i], urls[i], videoUrls[i]);
            mediaModels.add(mediaModel);
        }

        return mediaModels;
    }

    public static List<MediaModel> getVideoHorizontalModels() {
        List<MediaModel> mediaModels = new ArrayList<>();

        String titles[] = {
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
        };

        String contents[] = {
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
        };

        String urls[] = {
                "http://image.tmdb.org/t/p/w250_and_h141_bestv2/5LBcSLHAtEIIgvNkA2dPmYH5wR7.jpg",
                "http://image.tmdb.org/t/p/w250_and_h141_bestv2/4qfXT9BtxeFuamR4F49m2mpKQI1.jpg",
                "http://image.tmdb.org/t/p/w250_and_h141_bestv2/7mgKeg18Qml5nJQa56RBZO7dIu0.jpg",
                "http://image.tmdb.org/t/p/w250_and_h141_bestv2/tmFDgDmrdp5DYezwpL0ymQKIbnV.jpg",
                "http://image.tmdb.org/t/p/w250_and_h141_bestv2/rjUl3pd1LHVOVfG4IGcyA1cId5l.jpg",
                "http://image.tmdb.org/t/p/w250_and_h141_bestv2/3FweBee0xZoY77uO1bhUOlQorNH.jpg",
                "http://image.tmdb.org/t/p/w250_and_h141_bestv2/qhH3GyIfAnGv1pjdV3mw03qAilg.jpg",
                "http://image.tmdb.org/t/p/w250_and_h141_bestv2/5EQWRuCHkn7qGZytyBjv2lc9I4V.jpg",
        };

        String videoUrls[] = {
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
        };

        for (int i = 0; i < titles.length; i++) {
            MediaModel mediaModel = new MediaModel(
                    0, titles[i], contents[i], urls[i], videoUrls[i]);
            mediaModels.add(mediaModel);
        }

        return mediaModels;
    }


    public static List<MediaModel> getAppoModels() {
        List<MediaModel> mediaModels = new ArrayList<>();

        String titles[] = {
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
        };

        String contents[] = {
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
        };

        String urls[] = {
                "http://www.borongaja.com/data_images/out/26/669352-youtube-red-wallpaper.jpg",
                "http://sumroad.com/wp-content/uploads/2013/12/netflix-logo-300x168.png",
                "http://www.borongaja.com/data_images/out/26/669352-youtube-red-wallpaper.jpg",
                "http://sumroad.com/wp-content/uploads/2013/12/netflix-logo-300x168.png",
                "http://www.borongaja.com/data_images/out/26/669352-youtube-red-wallpaper.jpg",
                "http://sumroad.com/wp-content/uploads/2013/12/netflix-logo-300x168.png",
                "http://www.borongaja.com/data_images/out/26/669352-youtube-red-wallpaper.jpg",
                "http://sumroad.com/wp-content/uploads/2013/12/netflix-logo-300x168.png",
        };

        String videoUrls[] = {
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
        };

        for (int i = 0; i < titles.length; i++) {
            MediaModel mediaModel = new MediaModel(
                    0, titles[i], contents[i], urls[i], videoUrls[i]);
            mediaModels.add(mediaModel);
        }

        return mediaModels;
    }


    public static List<MediaModel> getLiveModels() {
        List<MediaModel> mediaModels = new ArrayList<>();

        String titles[] = {
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
                "Jason Bourne",
                "Rise",
        };

        String contents[] = {
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
                "Với những cảnh rượt đuổi, chiến đấu cực kỳ mãn nhãn, (trong đó, riêng cảnh rượt đuổi xe ở Las Vegas đã ngốn mất 5 tuần quay phim, phá hỏng 170 chiếc xe hơi), diễn xuất tuyệt vời của tài tử Oscar - Matt Damon trong vai người hùng bị tổn thương và săn đuổi ráo riết, phần 5 loạt phim về siêu điệp viên Bourne tiếp tục oanh tạc phòng vé, thu về 396,1 triệu USD, gấp hơn 3 lần kinh phí đầu tư. Sau một thập kỷ ẩn mình cô độc, cựu đặc vụ Jason Bourne tiếp tục bị CIA săn lùng khi anh cố tìm hiểu sự thật về người cha mình. Tất cả bắt đầu khi điệp viên Nicky Parsons đột nhập vào cơ sở dữ liệu của CIA, phát hiện ra bằng chứng về một chương trình bất hợp pháp của họ, cùng với thông tin chưa từng tiết lộ, làm đảo lộn mọi hiểu biết của Bourne về quá khứ của anh...",
                "Lấy cảm hứng từ chuyện đời có thật của bà trùm Dung Hà, bộ phim hành động nhiều bạo lực và cảnh nóng này đánh dấu sự lột xác của người đẹp Trương Ngọc Ánh trong một vai diễn phức tạp, vừa quyến rũ, nặng tình nặng nghĩa nhưng mặt khác lại dữ dội, giết người không ghê tay. Lớn lên giữa xóm chợ khắc nghiệt, lại bị người mình yêu thương phản bội, Diệu từ một cô gái trong sáng dần dần biến thành Hương Ga đáo để, dám đốt chợ, vào tù, giết người. Mối tình mãnh liệt với một tay anh chị chốn giang hồ càng khiến cô dấn sâu hơn vào thế giới xã hội đen, trở nên một bà trùm khét tiếng và là cái gai trong mắt nhiều kẻ thế lực..",
        };

        String urls[] = {
                "https://ast.fimplus.io/images/42511700-b058-11e6-8c91-c91b3c8346c2.jpg",
                "https://ast.fimplus.io/images/5c8e24f1-3146-11e6-bcf1-73a7816e5f55.jpg",
                "https://ast.fimplus.io/images/42511700-b058-11e6-8c91-c91b3c8346c2.jpg",
                "https://ast.fimplus.io/images/5c8e24f1-3146-11e6-bcf1-73a7816e5f55.jpg",
                "https://ast.fimplus.io/images/42511700-b058-11e6-8c91-c91b3c8346c2.jpg",
                "https://ast.fimplus.io/images/5c8e24f1-3146-11e6-bcf1-73a7816e5f55.jpg",
                "https://ast.fimplus.io/images/42511700-b058-11e6-8c91-c91b3c8346c2.jpg",
                "https://ast.fimplus.io/images/5c8e24f1-3146-11e6-bcf1-73a7816e5f55.jpg",
        };

        String videoUrls[] = {
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
                "http://eshare.live.otvcloud.com/otv/nyz/live/channel01/index.m3u8",
                "http://111.39.226.103:8112/120000001001/wlds:8080/ysten-business/live/hdhunanstv/.m3u8",
        };

        for (int i = 0; i < titles.length; i++) {
            MediaModel mediaModel = new MediaModel(
                    0, titles[i], contents[i], urls[i], videoUrls[i]);
            mediaModels.add(mediaModel);
        }

        return mediaModels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(imageUrl);
        dest.writeString(videoUrl);
    }
}
