//package cm.pdl.plandelocalisation.security.domain;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.Collection;
//
//import static javax.persistence.GenerationType.AUTO;
//
///**
// * @author Georges DEFO
// * @date 11/07/2022
// */
//@Entity
//@RequiredArgsConstructor
//@Getter
//@Setter
//public class AppUser {
//
//    @Id
//    @GeneratedValue(strategy = AUTO)
//    private Long id;
//    private String name;
//    private String username;
//    private String password;
//    @ManyToMany(fetch = FetchType.EAGER)
//    private Collection<AppRole> roles = new ArrayList<>();
//
//}
