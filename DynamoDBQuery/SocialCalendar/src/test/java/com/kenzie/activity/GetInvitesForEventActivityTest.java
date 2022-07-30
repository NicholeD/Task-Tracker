package com.kenzie.activity;

import com.kenzie.activity.dao.InviteDao;
import com.kenzie.activity.dao.models.Invite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetInvitesForEventActivityTest {
    @InjectMocks
    GetInvitesForEventActivity getInvitesForEventActivity;

    @Mock
    InviteDao inviteDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void handleRequest_withExclusiveStartKey_callsDao() {
        //GIVEN - WHEN
        List<Invite> invites = getInvitesForEventActivity.handleRequest("eventId",
                "exclusiveStartMemberId");

        //THEN
        verify(inviteDao).getInvitesForEvent("eventId", "exclusiveStartMemberId");
    }

    @Test
    public void getInvitesForEvent_withNullExclusiveStartKey_callsDao() {
        //GIVEN - WHEN
        List<Invite> invites = getInvitesForEventActivity.handleRequest("eventId", null);

        //THEN
        verify(inviteDao).getInvitesForEvent("eventId", null);
    }
}
