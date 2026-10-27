package com.example.app_uth_todo.screens
import androidx.compose.foundation.background
import com.example.app_uth_todo.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_uth_todo.models.OnboardingPage
import androidx.compose.foundation.Image
@Composable
fun OnboardingScreen(
    onNavigateToHome: () -> Unit // Hàm callback để chuyển hẳn sang trang khác
) {
    // Khởi tạo danh sách 3 đối tượng Onboarding (Áp dụng OOP)
    val pages = listOf(
        OnboardingPage(
            R.drawable.img_onboard_1,
            "Mọi việc, gọn trong một chỗ",
            "Ghi lại bài tập, việc nhà, lịch hẹn. Không\ncòn quên việc nào."
        ),
        OnboardingPage(
            R.drawable.img_onboard_2,
            "Chia theo Học tập và\nCuộc sống",
            "Mỗi việc có nhóm riêng, nhìn là biết hôm\nnay cần lo gì."
        ),
        OnboardingPage(
            R.drawable.img_onboard_3,
            "Nhắc đúng lúc",
            "Bật thông báo để UTH Todo nhắc bạn\ntrước hạn chót."
        )
    )


    // Biến lưu trạng thái xem đang ở trang số mấy (0, 1, hoặc 2)
    var currentPage by remember { mutableStateOf(0) }
    val pageData = pages[currentPage] // Lấy đối tượng hiện tại ra để hiển thị

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), // Tăng padding để có lề rộng như thiết kế
        horizontalAlignment = Alignment.CenterHorizontally // Căn giữa tất cả các thành phần
    ) {
        // 1. Phần Header: Logo UTH Todo và nút Bỏ qua
        // 1. Phần Header: Logo UTH Todo và nút Bỏ qua
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- CỤM LOGO BÊN TRÁI (Sửa ở đây) ---
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logo_uth), // Thay bằng ID icon của bạn
                    contentDescription = "Logo",
                    modifier = Modifier.size(28.dp) // Dùng size để icon vuông vắn
                )
                Spacer(modifier = Modifier.width(8.dp)) // Khoảng cách giữa icon và chữ
                Text(
                    text = "UTH ",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Todo",
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF395EE8) // Màu xanh của app
                )
            }
            // -------------------------------------

            // Nút Bỏ qua góc phải
            TextButton(onClick = { onNavigateToHome() }) {
                Text("Bỏ qua", color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // 2. Hình ảnh minh họa ở giữa
        Image(
            painter = painterResource(id = pageData.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp) // Điều chỉnh chiều cao cho phù hợp
        )

        Spacer(modifier = Modifier.height(48.dp))

        // 3. Tiêu đề và Mô tả (Căn trái theo thiết kế)
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = pageData.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = pageData.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // 4. Thanh chỉ báo (Page Indicator)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Start // Đặt dấu chấm bên trái
        ) {
            repeat(pages.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .height(8.dp)
                        .width(if (index == currentPage) 24.dp else 8.dp) // Dấu chấm hiện tại dài hơn
                        .background(
                            color = if (index == currentPage) Color(0xFF395EE8) else Color.LightGray,
                            shape = CircleShape // Bo tròn
                        )
                )
            }
        }

        // 5. Nút Tiếp tục / Bắt đầu ngay
        Button(
            onClick = {
                if (currentPage < pages.size - 1) {
                    currentPage++
                } else {
                    onNavigateToHome()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF395EE8)) // Màu xanh dương
        ) {
            Text(if (currentPage == pages.size - 1) "Bắt đầu ngay" else "Tiếp tục", color = Color.White)
        }
    }
}