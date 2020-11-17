<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

<head>
    <title>티구시포</title>
    <style>
        .line {
            overflow: hidden;
        }
        .seat {
            margin: 6px;
            float: left;
            width: 100px;
            height: 100px;
            border-radius: 3px;
        }
        .enable {
            background: gray;
        }
        .enable:hover {
            background: black;
        }
        .disable {
            background: red;
        }
        .center{
          position: absolute;
          left: 50%;
          margin-left: -150px;
          top: 50%;
          margin-top: -150px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-1.12.1.js"></script>
    <script src="/socket.io/socket.io.js"></script>
    <!-- 소켓 생성 -->
    <script>
        // 소켓 이벤트를 수행합니다.
        var socket = io();

        // 이벤트를 연결합니다.
        socket.on('reserve', function (data) {
            var $target = $('div[data-x = ' + data.x + '][data-y = ' + data.y + ']');
            $target.removeClass('enable');
            $target.addClass('disable');
        });
    </script>
    <!-- 초기 좌석 생성 -->
    <script>
        $(document).ready(function () {
            // 변수를 선언합니다.
            var onClickSeat = function () {
                var x = $(this).attr('data-x');
                var y = $(this).attr('data-y');
                if (confirm('좌석을 예약하시겠습니까?')) {
                    $(this).off('click');
                    socket.emit('reserve', {
                        x: x,
                        y: y
                    });
                } else {
                    alert('취소되었습니다.');
                }
            };
            // Ajax를 수행합니다.
            $.getJSON('/seatmanager/seats', { dummy: new Date().getTime() }, function (data) {
                // 좌석을 생성합니다.
                $.each(data, function (indexY, line) {
                    // 문서 객체를 생성합니다.
                    var $line = $('<div></div>').addClass('line');
                    $.each(line, function (indexX, seat) {
                        // 문서 객체를 생성하고 변수 $line에 추가합니다.
                        var $output = $('<div></div>', {
                            'class': 'seat',
                            'data-x': indexX,
                            'data-y': indexY
                        }).appendTo($line);
                        if (seat == 1) {
                            // 좌석이 비어 있으면 enable 클래스와 click 리스너를 추가합니다.
                            $output.addClass('enable').on('click', onClickSeat);
                        } else if (seat == 2) {
                            // 좌석이 사용 불가능하면 disable 클래스를 추가합니다.
                            $output.addClass('disable');
                        }
                    });
                    // 문서 객체를 추가합니다.
                    $line.appendTo('.center');
                });
            });
        });
    </script>
</head>
<body>
  <div class="center">

  </div>
</body>
</html>
