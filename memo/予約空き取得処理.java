Service


@Service
public class AvailabilityService {

    private final ReservationsRepository reservationsRepository;
    private final TimeRepository timeRepository;
    private final SevenDatesGetService sevenDatesGetService;

    public AvailabilityService(
        ReservationsRepository reservationsRepository,
        TimeRepository timeRepository,
        SevenDatesGetService sevenDatesGetService
    ) {
        this.reservationsRepository = reservationsRepository;
        this.timeRepository = timeRepository;
        this.sevenDatesGetService = sevenDatesGetService;
    }

    public Map<LocalDate, Map<Integer, Boolean>> getAvailability(Integer interviewerId) {

        List<LocalDate> dates = sevenDatesGetService.getDates();
        List<Time> times = timeRepository.findAll();
        List<Reservation> reservations =
            reservationsRepository.findByInterviewerId(interviewerId);

        Set<String> reservedKeys = reservations.stream()
            .map(r -> r.getDate() + "_" + r.getTime().getId())
            .collect(Collectors.toSet());

        Map<LocalDate, Map<Integer, Boolean>> availability = new LinkedHashMap<>();

        for (LocalDate date : dates) {
            Map<Integer, Boolean> timeMap = new LinkedHashMap<>();
            for (Time time : times) {
                String key = date + "_" + time.getId();
                timeMap.put(time.getId(), !reservedKeys.contains(key));
            }
            availability.put(date, timeMap);
        }

        return availability;
    }
}





Controller

@GetMapping("/reservation_ables_get")
public String show(
        @RequestParam("interviewer_id") Integer interviewerId,
        Model model) {

    Map<LocalDate, Map<Integer, Boolean>> availability =
        availabilityService.getAvailability(interviewerId);

    model.addAttribute("availability", availability);
    model.addAttribute("dates", availability.keySet());
    model.addAttribute("times", timeRepository.findAll());

    return "reservation_ables";
}


(modelではなくredirectAttribute)


html


<tr th:each="time : ${times}">
    <td th:text="${time.start}">15</td>  <!-- 左端の時間表示 -->

    <td th:each="date : ${dates}">
        <span th:if="${availability[date][time.start]}">○</span>
        <span th:unless="${availability[date][time.start]}">×</span>
    </td>
</tr>


(すべてにおいて、time.idは、time.startに変更)



