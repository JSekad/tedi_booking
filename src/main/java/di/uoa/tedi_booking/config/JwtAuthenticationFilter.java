package di.uoa.tedi_booking.config;

import di.uoa.tedi_booking.entities.Role;
import di.uoa.tedi_booking.entities.User;
import di.uoa.tedi_booking.DTOS.repositories.RoleRepository;
import di.uoa.tedi_booking.DTOS.repositories.UserRepository;
import di.uoa.tedi_booking.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userName;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        userName = jwtService.extractUserName(jwt);//todo
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // Parse the JSON string
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonNode = objectMapper.readTree(userName);
//            String uname = jsonNode.get("username").asText();
            User user = null;
            if (userName.equals("admin")){
                user = new User();
                user.setUserName("admin");

                Role adminRole = roleRepository.findById(1L).orElse(null);
                Set<Role> rolesSet = new HashSet<Role>();
                rolesSet.add(adminRole);
                user.setRoles(rolesSet);
//                user.setPerson(null);
            }else {
                user = userRepository.findAllByUserName(userName)
                        .orElseThrow();
            }
            if(jwtService.isTokenValid(jwt,user)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
